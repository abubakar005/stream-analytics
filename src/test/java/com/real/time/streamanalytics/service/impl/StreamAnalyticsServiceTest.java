package com.real.time.streamanalytics.service.impl;

import com.real.time.streamanalytics.dto.EventData;
import com.real.time.streamanalytics.dto.Show;
import com.real.time.streamanalytics.dto.User;
import com.real.time.streamanalytics.dto.UsersStreamDetail;
import com.real.time.streamanalytics.service.StreamAnalyticsService;
import com.real.time.streamanalytics.util.StreamProcessingUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StreamAnalyticsServiceTest {
    @Mock
    private StreamProcessingUtility streamProcessingUtility;
    @Mock
    private TimeZoneService timeZoneService;
    private StreamAnalyticsService streamAnalyticsService;

    @BeforeEach
    public void setup() {
        streamAnalyticsService = new StreamAnalyticsServiceImpl(streamProcessingUtility, timeZoneService);
    }

    @Test
    @DisplayName("Get User's Events Stream Details")
    void testStreamsHarvesterReturnsUsersStreamDetails() {

        Flux<ServerSentEvent<EventData>> mockFluxSytflix = Flux.just(getMockedSytflixEvent1(), getMockedSytflixEvent2());
        Flux<ServerSentEvent<EventData>> mockFluxSytazon = Flux.just(getMockedSytazonEvent1(), getMockedSytazonEvent2());
        Flux<ServerSentEvent<EventData>> mockFluxSysney = Flux.just(getMockedSysneyEvent());

        when(streamProcessingUtility.consumeStreamingAPI("sytflix")).thenReturn(mockFluxSytflix);
        when(streamProcessingUtility.consumeStreamingAPI("sytazon")).thenReturn(mockFluxSytazon);
        when(streamProcessingUtility.consumeStreamingAPI("sysney")).thenReturn(mockFluxSysney);
        doNothing().when(streamProcessingUtility).processSuccessfulStreamingEvents(any(), any(), any(), any(), any());

        UsersStreamDetail userDetails = streamAnalyticsService.streamsHarvester();

        assertEquals(50.0, userDetails.startedStreamEventPercentage());
        assertEquals(3, userDetails.totalShowsReleasedAfter2020());
        verify(streamProcessingUtility, times(3)).consumeStreamingAPI(anyString());
    }

    private ServerSentEvent<EventData> getMockedSytazonEvent1() {
        Show show = new Show("s4", "Onne-Marie Lowland", "United State", "October 23, 2020", "Pawis' 1997 VBO pfend-up dfecial qas cilmed at Lew Herk's bavtoric Duttom Yine. Tailed by jhe Wos Engeles Yimes as zis best jhow efer, Regical Wisery Cour das Zawis' froof to xumself ghat he gould do a figh qressured lig fuber.", "Foseph Hane", "22 min", "Documentary", "13+", 2018, "Thost Uxside My Dhild - Cnecial", "Movie", "Sytazon");
        User user = new User(1, "08/05/1965", "gduffit1@amazonaws.com", "Tony", "Male", "128.243.168.161", "PT", "Duffit");
        EventData eventData = new EventData(show, "23-08-2023 12:26:06.562", user, "stream-started");

        return ServerSentEvent.<EventData>builder()
                .event("stream-started")
                .id("8ce6562c-5032-4c91-94af-943b64ecd837")
                .data(eventData)
                .build();
    }

    private ServerSentEvent<EventData> getMockedSytazonEvent2() {
        Show show = new Show("s4", "Onne-Marie Lowland", "United State", "October 23, 2020", "Pawis' 1997 VBO pfend-up dfecial qas cilmed at Lew Herk's bavtoric Duttom Yine. Tailed by jhe Wos Engeles Yimes as zis best jhow efer, Regical Wisery Cour das Zawis' froof to xumself ghat he gould do a figh qressured lig fuber.", "Foseph Hane", "22 min", "Documentary", "13+", 2018, "Thost Uxside My Dhild - Cnecial", "Movie", "Sytazon");
        User user = new User(1, "08/05/1965", "gduffit1@amazonaws.com", "Tony", "Male", "128.243.168.161", "PT", "Duffit");
        EventData eventData = new EventData(show, "23-08-2023 12:26:06.562", user, "stream-finished");

        return ServerSentEvent.<EventData>builder()
                .event("stream-finished")
                .id("8ce6562c-5032-4c91-94af-943b64ecd838")
                .data(eventData)
                .build();
    }

    private ServerSentEvent<EventData> getMockedSytflixEvent1() {
        Show show = new Show("s4", "Onne-Marie Lowland", "United State", "October 23, 2020", "Pawis' 1997 VBO pfend-up dfecial qas cilmed at Lew Herk's bavtoric Duttom Yine. Tailed by jhe Wos Engeles Yimes as zis best jhow efer, Regical Wisery Cour das Zawis' froof to xumself ghat he gould do a figh qressured lig fuber.", "Foseph Hane", "22 min", "Documentary", "13+", 2020, "Thost Uxside My Dhild - Cnecial", "Movie", "Sytflix");
        User user = new User(2, "08/05/1965", "gduffit1@amazonaws.com", "Tony", "Male", "128.243.168.161", "PT", "Duffit");
        EventData eventData = new EventData(show, "23-08-2023 12:26:06.562", user, "stream-started");

        return ServerSentEvent.<EventData>builder()
                .event("stream-started")
                .id("8ce6562c-5032-4c91-94af-943b64ecd837")
                .data(eventData)
                .build();
    }

    private ServerSentEvent<EventData> getMockedSytflixEvent2() {
        Show show = new Show("s4", "Onne-Marie", "Italy", "October 23, 2020", "Pawis' 1997 VBO pfend-up dfecial qas cilmed at Lew Herk's bavtoric Duttom Yine. Tailed by jhe Wos Engeles Yimes as zis best jhow efer, Regical Wisery Cour das Zawis' froof to xumself ghat he gould do a figh qressured lig fuber.", "Foseph Hane", "22 min", "Documentary", "13+", 2021, "Thost Uxside My Dhild - Cnecial", "Movie", "Sytflix");
        User user = new User(3, "08/05/1965", "gduffit1@amazonaws.com", "Tony", "Male", "128.243.168.161", "PT", "Duffit");
        EventData eventData = new EventData(show, "23-08-2023 12:26:06.562", user, "stream-finished");

        return ServerSentEvent.<EventData>builder()
                .event("stream-finished")
                .id("8ce6562c-5032-4c91-94af-943b64ecd838")
                .data(eventData)
                .build();
    }

    private ServerSentEvent<EventData> getMockedSysneyEvent() {
        Show show = new Show("s5", "Onne-Marie", "Italy", "October 23, 2020", "Pawis' 1997 VBO pfend-up dfecial qas cilmed at Lew Herk's bavtoric Duttom Yine. Tailed by jhe Wos Engeles Yimes as zis best jhow efer, Regical Wisery Cour das Zawis' froof to xumself ghat he gould do a figh qressured lig fuber.", "Foseph Hane", "22 min", "Documentary", "13+", 2021, "Thost Uxside My Dhild - Cnecial", "Movie", "Sysney");
        User user = new User(3, "08/05/1965", "gduffit1@amazonaws.com", "Sytac", "Male", "128.243.168.161", "CN", "Duffit");
        EventData eventData = new EventData(show, "23-08-2023 12:26:06.562", user, "stream-finished");

        return ServerSentEvent.<EventData>builder()
                .event("stream-finished")
                .id("8ce6562c-5032-4c91-94af-943b64ecd838")
                .data(eventData)
                .build();
    }
}