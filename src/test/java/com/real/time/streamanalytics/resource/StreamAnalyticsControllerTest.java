package com.real.time.streamanalytics.resource;

import com.real.time.streamanalytics.dto.UsersStreamDetail;
import com.real.time.streamanalytics.service.StreamAnalyticsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StreamAnalyticsControllerTest {

    @InjectMocks
    private StreamAnalyticsController controller;
    @Mock
    private StreamAnalyticsService service;


    @Test
    @DisplayName("Test Get Stream Details API - Successful Scenario")
    void testGetStreamDetailsReturnsOK() {
        UsersStreamDetail userDetails = getMockedUsersStreamDetail();
        when(service.streamsHarvester()).thenReturn(userDetails);

        ResponseEntity<UsersStreamDetail> response = controller.getStreamDetails();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDetails, response.getBody());

        verify(service, times(1)).streamsHarvester();
    }

    @Test
    @DisplayName("Test Get Stream Details API - Internal Server Error Scenario")
    void testGetStreamDetailsInternalServerError() {
        when(service.streamsHarvester()).thenThrow(new RuntimeException("Internal server error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> controller.getStreamDetails());
        assertEquals("Internal server error", exception.getMessage());

        verify(service, times(1)).streamsHarvester();
    }

    private UsersStreamDetail getMockedUsersStreamDetail() {
        return new UsersStreamDetail(5, new ArrayList<>(), 5520, 75.0);
    }
}