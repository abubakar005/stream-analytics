package com.real.time.streamanalytics.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Details of a streaming event")
public record EventDetail(
        @Schema(description = "Type of event", example = "stream-started")
        String event,

        @Schema(description = "ID of the show", example = "s01")
        String showId,

        @Schema(description = "Title of the show", example = "My Awesome Show")
        String showTitle,

        @Schema(description = "Platform where the show is streamed", example = "Sytflix")
        String platform,

        @Schema(description = "Name of the first cast person", example = "John Doe")
        String firstCastPerson,

        @Schema(description = "Time of the event", example = "21-08-2023 15:11:05.657")
        String time
) {
}
