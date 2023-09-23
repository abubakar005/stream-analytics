package com.real.time.streamanalytics.service.impl;

import com.real.time.streamanalytics.config.TimeZoneProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TimeZoneServiceTest {

    @Mock
    private TimeZoneProperties timeZoneProperties;

    private TimeZoneService timeZoneService;

    @BeforeEach
    void setUp() {
        timeZoneService = new TimeZoneService(timeZoneProperties);
    }

    @Test
    @DisplayName("Convert US Time To Amsterdam Time With Valid Country Code")
    void testConvertToAmsterdamTimeWithValidCountryCode() {

        String countryCode = "US";
        String eventDate = "26-08-2023 12:30:10.560";

        when(timeZoneProperties.countryCodeToTimeZone()).thenReturn(getMockedCountryCodeMapping());

        String amsterdamTime = timeZoneService.convertToAmsterdamTime(countryCode, eventDate);

        assertNotNull(amsterdamTime);
        assertEquals("26-08-2023 21:30:10.560", amsterdamTime);
    }

    @Test
    @DisplayName("Convert To Amsterdam Time With Invalid Country Code")
    void testConvertToAmsterdamTimeWithInvalidCountryCode() {

        String countryCode = "NA";
        String eventDate = "26-08-2023 12:30:10.560";

        when(timeZoneProperties.countryCodeToTimeZone()).thenReturn(getMockedCountryCodeMapping());

        String amsterdamTime = timeZoneService.convertToAmsterdamTime(countryCode, eventDate);

        assertNotNull(amsterdamTime);
        assertEquals(eventDate, amsterdamTime);
    }

    private Map<String, String> getMockedCountryCodeMapping() {
        return Map.of("PT", "UTC",
                "CA", "America/Toronto",
                "US", "America/Los_Angeles",
                "RU", "Europe/Moscow",
                "ID", "Asia/Jakarta",
                "CN", "Asia/Shanghai");
    }
}