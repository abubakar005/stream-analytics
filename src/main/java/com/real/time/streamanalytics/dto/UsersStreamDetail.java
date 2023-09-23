package com.real.time.streamanalytics.dto;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Details of user streaming analytics")
public record UsersStreamDetail(
        @Schema(description = "Total number of shows released after or in 2020", example = "50")
        int totalShowsReleasedAfter2020,

        @Schema(description = "List of user details including streaming events")
        List<UserDetail> users,

        @Schema(description = "Duration of stream APIs consumption in milliseconds", example = "15000")
        long streamConsumptionDurationMillis,

        @Schema(description = "Percentage of started stream events out of all events occurred on a Sytflix platform", example = "75.0")
        double startedStreamEventPercentage
) {
}
