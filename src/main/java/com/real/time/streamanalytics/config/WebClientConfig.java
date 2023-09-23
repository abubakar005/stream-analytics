package com.real.time.streamanalytics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${streaming.server.api.url}")
    private String API_BASIC_URL;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(API_BASIC_URL)
                .build();
    }
}

