package com.real.time.streamanalytics.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Information about a streaming event")
public record EventStreamInfo(
        @Schema(description = "Type of event", example = "stream-started")
        String event,

        @Schema(description = "ID of the show", example = "s01")
        String showId,

        @Schema(description = "Platform where the show is streamed", example = "Sytflix")
        String platform
) {
}
