package com.real.time.streamanalytics.resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "streaming.server.duration=1",
})
class StreamAnalyticsControllerIntegrationTest {

    private String API_URL = "http://localhost:%s/api/v1/stream/details";

    @LocalServerPort
    private int port;

    HttpClient httpClient = HttpClient.newHttpClient();

    @Test
    @DisplayName("Test API endpoint returns HTTP status OK")
    void testApiEndpointReturnsHttpStatusOK() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(API_URL, port)))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(HttpStatus.OK.value(), response.statusCode());
    }
}