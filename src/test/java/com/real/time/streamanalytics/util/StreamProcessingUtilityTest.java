package com.real.time.streamanalytics.util;

import com.real.time.streamanalytics.dto.EventData;
import com.real.time.streamanalytics.dto.EventStreamInfo;
import com.real.time.streamanalytics.dto.Show;
import com.real.time.streamanalytics.dto.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StreamProcessingUtilityTest {

    @Mock
    private WebClient webClientMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @InjectMocks
    private StreamProcessingUtility streamProcessingUtility;

    @Test
    @DisplayName("Consume Streaming API Using Webclient")
    public void testConsumeStreamingAPIUsingWebclient() {

        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.header(Utility.HEADER_AUTHORIZATION, Utility.getBasicAuthHeader())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux((new ParameterizedTypeReference<ServerSentEvent<EventData>>() {}))).thenReturn(Flux.just(getMockedServerSentEventSysney()));

        streamProcessingUtility.consumeStreamingAPI("sysney");
    }

    @Test
    @DisplayName("Process Successful Streaming Events on Sytflix")
    void testProcessSuccessfulStreamingEventsSytflix() {

        Map<Long, EventStreamInfo> streamInfoSytflix = getMockedHashMap();
        Map<Long, Integer> usersSuccessfulStreams = new ConcurrentHashMap<>();

        streamProcessingUtility.processSuccessfulStreamingEvents(getMockedEventSytflix(), usersSuccessfulStreams, streamInfoSytflix, null, null);
        assertEquals(1, usersSuccessfulStreams.size());
        assertEquals(1, usersSuccessfulStreams.get(6L));
    }

    @Test
    @DisplayName("Process Successful Streaming Events on Sytazon")
    void testProcessSuccessfulStreamingEventsSytazon() {

        Map<Long, EventStreamInfo> streamInfoSytazon = getMockedHashMap();
        Map<Long, Integer> usersSuccessfulStreams = new ConcurrentHashMap<>();

        streamProcessingUtility.processSuccessfulStreamingEvents(getMockedEventSytazon(), usersSuccessfulStreams, null, streamInfoSytazon, null);
        assertEquals(0, usersSuccessfulStreams.size());
    }

    @Test
    @DisplayName("Process Successful Streaming Events on Sysney")
    void testProcessSuccessfulStreamingEventsSysney() {

        Map<Long, EventStreamInfo> streamInfoSysney = getMockedHashMap();
        Map<Long, Integer> usersSuccessfulStreams = new ConcurrentHashMap<>();

        streamProcessingUtility.processSuccessfulStreamingEvents(getMockedEventSysney(), usersSuccessfulStreams, null, null, streamInfoSysney);
        assertEquals(0, usersSuccessfulStreams.size());
    }

    private Map<Long, EventStreamInfo> getMockedHashMap() {
        Map<Long, EventStreamInfo> concurrentHashMap = new ConcurrentHashMap<>();
        EventStreamInfo eventStreamInfo = new EventStreamInfo("stream-started", "s5", "Sytflix");
        concurrentHashMap.put(6L, eventStreamInfo);
        return concurrentHashMap;
    }

    private ServerSentEvent<EventData> getMockedServerSentEventSysney() {
        return ServerSentEvent.<EventData>builder()
                .event("stream-finished")
                .id("8ce6562c-5032-4c91-94af-943b64ecd838")
                .data(getMockedEventSysney())
                .build();
    }

    private EventData getMockedEventSytflix() {
        Show show = new Show("s5", "Onne-Marie", "Italy", "October 23, 2020", "Pawis' 1997 VBO pfend-up dfecial qas cilmed at Lew Herk's bavtoric Duttom Yine. Tailed by jhe Wos Engeles Yimes as zis best jhow efer, Regical Wisery Cour das Zawis' froof to xumself ghat he gould do a figh qressured lig fuber.", "Foseph Hane", "22 min", "Documentary", "13+", 2021, "Thost Uxside My Dhild - Cnecial", "Movie", "Sytflix");
        User user = new User(6, "08/05/1965", "gduffit1@amazonaws.com", "Sytac", "Male", "128.243.168.161", "CN", "Duffit");
        return new EventData(show, "23-08-2023 12:26:06.562", user, "stream-finished");
    }

    private EventData getMockedEventSytazon() {
        Show show = new Show("s5", "Onne-Marie", "Italy", "October 23, 2020", "Pawis' 1997 VBO pfend-up dfecial qas cilmed at Lew Herk's bavtoric Duttom Yine. Tailed by jhe Wos Engeles Yimes as zis best jhow efer, Regical Wisery Cour das Zawis' froof to xumself ghat he gould do a figh qressured lig fuber.", "Foseph Hane", "22 min", "Documentary", "13+", 2021, "Thost Uxside My Dhild - Cnecial", "Movie", "Sytazon");
        User user = new User(6, "08/05/1965", "gduffit1@amazonaws.com", "Sytac", "Male", "128.243.168.161", "CN", "Duffit");
        return new EventData(show, "23-08-2023 12:26:06.562", user, "stream-finished");
    }

    private EventData getMockedEventSysney() {
        Show show = new Show("s5", "Onne-Marie", "Italy", "October 23, 2020", "Pawis' 1997 VBO pfend-up dfecial qas cilmed at Lew Herk's bavtoric Duttom Yine. Tailed by jhe Wos Engeles Yimes as zis best jhow efer, Regical Wisery Cour das Zawis' froof to xumself ghat he gould do a figh qressured lig fuber.", "Foseph Hane", "22 min", "Documentary", "13+", 2021, "Thost Uxside My Dhild - Cnecial", "Movie", "Sysney");
        User user = new User(6, "08/05/1965", "gduffit1@amazonaws.com", "Sytac", "Male", "128.243.168.161", "CN", "Duffit");
        return new EventData(show, "23-08-2023 12:26:06.562", user, "stream-finished");
    }
}