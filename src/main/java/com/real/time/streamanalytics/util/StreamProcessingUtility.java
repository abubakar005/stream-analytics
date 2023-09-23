package com.real.time.streamanalytics.util;

import com.real.time.streamanalytics.dto.EventData;
import com.real.time.streamanalytics.dto.EventStreamInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class StreamProcessingUtility {

    private final WebClient webClient;

    @Value("${streaming.server.duration:20}")
    private long EVENT_STREAMING_DURATION;

    public Flux<ServerSentEvent<EventData>> consumeStreamingAPI(String apiUrl) {
        return webClient
                .get()
                .uri(apiUrl)
                .header(Utility.HEADER_AUTHORIZATION, Utility.getBasicAuthHeader())
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<ServerSentEvent<EventData>>() {})
                .onErrorContinue((error, value) -> {
                    // Handle the error, and continue with the next value (event)
                    log.error("Buggy data. JSON parsing error.");
                })
                .take(Duration.ofSeconds(EVENT_STREAMING_DURATION));
    }

    public void processSuccessfulStreamingEvents(EventData event,
                                                  Map<Long, Integer> usersSuccessfulStreams,
                                                  Map<Long, EventStreamInfo> streamInfoSytflix,
                                                  Map<Long, EventStreamInfo> streamInfoSytazon,
                                                  Map<Long, EventStreamInfo> streamInfoSysney) {
        if (event.show().platform().equals(Utility.PLATFORM_SYTFLIX)) {
            processSuccessfulStreamingEvent(event, streamInfoSytflix, usersSuccessfulStreams);
        } else if (event.show().platform().equals(Utility.PLATFORM_SYTAZON)) {
            processSuccessfulStreamingEvent(event, streamInfoSytazon, usersSuccessfulStreams);
        } else if (event.show().platform().equals(Utility.PLATFORM_SYSNEY)) {
            processSuccessfulStreamingEvent(event, streamInfoSysney, usersSuccessfulStreams);
        }
    }

    private void processSuccessfulStreamingEvent(EventData event, Map<Long, EventStreamInfo> streamInfoMap, Map<Long, Integer> usersSuccessfulStreamsMap) {

        long userId = event.user().id();

        if (streamInfoMap.containsKey(userId)) {
            EventStreamInfo eventStreamInfo = streamInfoMap.get(userId);

            if (event.name().equals(Utility.STREAM_FINISHED)
                    && event.show().showId().equals(eventStreamInfo.showId())
                    && event.show().platform().equals(eventStreamInfo.platform())) {
                usersSuccessfulStreamsMap.putIfAbsent(userId, Utility.INDEX_ZERO);
                usersSuccessfulStreamsMap.put(event.user().id(), usersSuccessfulStreamsMap.get(event.user().id()) + Utility.INDEX_ONE);
            }
            streamInfoMap.remove(userId);
        }

        if (event.name().equals(Utility.STREAM_STARTED)) {
            streamInfoMap.put(userId, new EventStreamInfo(event.name(), event.show().showId(), event.show().platform()));
        }
    }
}
