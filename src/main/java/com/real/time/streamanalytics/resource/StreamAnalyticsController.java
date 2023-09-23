package com.real.time.streamanalytics.resource;

import com.real.time.streamanalytics.dto.UsersStreamDetail;
import com.real.time.streamanalytics.service.StreamAnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/stream")
@RequiredArgsConstructor
@Tag(name = "Streaming Analytics Controller", description = "API for retrieving users streaming analytics")
public class StreamAnalyticsController {

    private final StreamAnalyticsService streamAnalyticsService;

    @Operation(summary = "Get User's Streaming Details",
            description = "Retrieves detailed streaming analytics for users, including successful streams and more.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Streams data successfully processed",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsersStreamDetail.class)) }),
            @ApiResponse(responseCode = "404", description = "Data not found",
                    content = @Content) })
    @GetMapping("/details")
    public ResponseEntity<UsersStreamDetail> getStreamDetails() {
        UsersStreamDetail userDetails = streamAnalyticsService.streamsHarvester();
        return ResponseEntity.ok(userDetails);
    }
}
