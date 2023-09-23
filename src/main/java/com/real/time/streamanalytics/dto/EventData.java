package com.real.time.streamanalytics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Streaming event data")
public record EventData(
        @Schema(description = "Details of the show")
        Show show,

        @JsonProperty("event_date")
        @Schema(description = "Date of the event", example = "21-08-2023 15:11:05.657")
        String eventDate,

        @Schema(description = "User associated with the event")
        User user,

        @Schema(description = "Name of the event", example = "stream-started")
        String name
) {

    public EventData withEventName(String name) {
        return new EventData(show(), eventDate(), user(), name);
    }
}

