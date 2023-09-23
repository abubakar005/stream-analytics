package com.real.time.streamanalytics.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Details of a user including streaming events")
public record UserDetail(
        @Schema(description = "ID of the user", example = "12345")
        long id,

        @Schema(description = "First name of the user", example = "John")
        String name,

        @Schema(description = "Last name of the user", example = "Doe")
        String surname,

        @Schema(description = "Age of the user", example = "30")
        int age,

        @Schema(description = "Number of successful streaming events for the user", example = "5")
        int successfulStreamEvents,

        @Schema(description = "List of streaming events for the user")
        List<EventDetail> userEvents
) {
}

