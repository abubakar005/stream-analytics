package com.real.time.streamanalytics.service.impl;

import com.real.time.streamanalytics.config.TimeZoneProperties;
import com.real.time.streamanalytics.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class TimeZoneService {

    private final TimeZoneProperties timeZoneProperties;

    public String convertToAmsterdamTime(String countryCode, String eventDate) {
        String timeZoneId = timeZoneProperties.countryCodeToTimeZone().get(countryCode);

        if (timeZoneId == null) {
            log.info("Country code {} not found in the list: ", countryCode);
            return eventDate;
        }

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(Utility.DATE_TIME_FORMAT);
        LocalDateTime localDateTime = LocalDateTime.parse(eventDate, inputFormatter);

        ZoneId zone = ZoneId.of(timeZoneId);
        ZonedDateTime countryTime = localDateTime.atZone(zone);

        ZoneId amsterdamZone = ZoneId.of(Utility.AMSTERDAM_ZONE_ID);
        ZonedDateTime amsterdamTime = countryTime.withZoneSameInstant(amsterdamZone);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(Utility.DATE_TIME_FORMAT);
        return amsterdamTime.format(outputFormatter);
    }
}

