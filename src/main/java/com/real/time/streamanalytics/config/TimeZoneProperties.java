package com.real.time.streamanalytics.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TimeZoneProperties {

    @Bean
    @ConfigurationProperties(prefix = "app.timezone.country-code-to-timezone")
    public Map<String, String> countryCodeToTimeZone() {
        return new HashMap<>();
    }
}

