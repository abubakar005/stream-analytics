package com.real.time.streamanalytics.service.impl;

import com.real.time.streamanalytics.dto.*;
import com.real.time.streamanalytics.service.StreamAnalyticsService;
import com.real.time.streamanalytics.util.StreamProcessingUtility;
import com.real.time.streamanalytics.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@RequiredArgsConstructor
public class StreamAnalyticsServiceImpl implements StreamAnalyticsService {

    private final StreamProcessingUtility streamProcessingUtility;
    private final TimeZoneService timeZoneService;

    @Override
    public UsersStreamDetail streamsHarvester() {

        Map<Long, EventStreamInfo> streamInfoSytflix = new ConcurrentHashMap<>();
        Map<Long, EventStreamInfo> streamInfoSytazon = new ConcurrentHashMap<>();
        Map<Long, EventStreamInfo> streamInfoSysney = new ConcurrentHashMap<>();
        Map<Long, Integer> usersSuccessfulStreams = new ConcurrentHashMap<>();
        var numberOfShowsReleasedAfterOrIn2020 = new AtomicInteger(Utility.INDEX_ZERO);
        var sytacCount = new AtomicInteger(Utility.INDEX_ZERO);
        var startedEventsSytflix = new AtomicInteger(Utility.INDEX_ZERO);
        var totalEventsSytflix = new AtomicInteger(Utility.INDEX_ZERO);
        long startTime = System.currentTimeMillis();

        var fluxSytflix = streamProcessingUtility.consumeStreamingAPI("sytflix");
        var fluxSytazon = streamProcessingUtility.consumeStreamingAPI("sytazon");
        var fluxSysney = streamProcessingUtility.consumeStreamingAPI("sysney");

        var userDetailFlux = Flux.merge(fluxSytflix, fluxSytazon, fluxSysney)
                .parallel() // Enable parallel processing
                .runOn(Schedulers.parallel()) // Use parallel scheduler
                .sequential()
                .transform(flux -> flux.takeWhile(event -> {
                    if (event.data().user().firstName().equals(Utility.FIRST_NAME_SYTAC)) {
                        int count = sytacCount.incrementAndGet();
                        return count < Utility.SYTAC_NAME_MAX_VALUE; // Take events until the third occurrence of "Sytac"
                    }
                    return true;
                }))
                .map(serverSentEvent -> serverSentEvent.data().withEventName(serverSentEvent.event()))
                .doOnNext(event -> {
                    // Count shows released after or in 2020
                    if (event.show().releaseYear() >= Utility.RELEASE_YEAR_2020)
                        numberOfShowsReleasedAfterOrIn2020.incrementAndGet();

                    // Checking successful streaming events on all platforms
                    streamProcessingUtility.processSuccessfulStreamingEvents(event, usersSuccessfulStreams, streamInfoSytflix, streamInfoSytazon, streamInfoSysney);
                })
                .publishOn(Schedulers.parallel()) // Switch back to parallel scheduler
                //.sequential() // Switch back to sequential processing
                .groupBy(event -> event.user().id()) // Group by user IDs
                .flatMap(group -> group
                        .collectList()
                        .map(eventsList -> {
                            User user = eventsList.get(Utility.INDEX_ZERO).user();
                            List<EventDetail> events = new LinkedList<>();
                            int userSuccessfulEvents = usersSuccessfulStreams.get(user.id()) == null ? Utility.INDEX_ZERO : usersSuccessfulStreams.get(user.id());
                            UserDetail userDetail = new UserDetail(user.id(), user.firstName(), user.lastName(),
                                    Utility.calculateAge(user.dateOfBirth()), userSuccessfulEvents, events);
                            eventsList.forEach(event -> {
                                if(event.show().platform().equals(Utility.PLATFORM_SYTFLIX)) {
                                    if (event.name().equals(Utility.STREAM_STARTED))
                                        startedEventsSytflix.incrementAndGet();

                                    totalEventsSytflix.incrementAndGet();
                                }
                                String currentZoneTime = timeZoneService.convertToAmsterdamTime(user.country(), event.eventDate());
                                Show show = event.show();
                                EventDetail eventDetail = new EventDetail(event.name(), show.showId(), show.title(), show.platform(), show.cast() != null ? show.cast().split(Utility.REGEX_COMMA)[Utility.INDEX_ZERO] : null, currentZoneTime);
                                events.add(eventDetail);
                            });
                            return userDetail;
                        })
                );

        // Blocking here to get the result
        var userDetailsList = userDetailFlux.collectList().block();
        long endTime = System.currentTimeMillis();
        long timeTakenMillis = endTime - startTime;

        return new UsersStreamDetail(
                numberOfShowsReleasedAfterOrIn2020.get(),
                userDetailsList,
                timeTakenMillis,
                Utility.calculatePercentage(startedEventsSytflix.get(), totalEventsSytflix.get())
        );
    }
}
