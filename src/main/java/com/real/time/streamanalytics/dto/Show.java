package com.real.time.streamanalytics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Details of a show")
public record Show(
        @JsonProperty("show_id")
        @Schema(description = "ID of the show", example = "s_123")
        String showId,

        @Schema(description = "Cast of the show", example = "Cole Biplan")
        String cast,

        @Schema(description = "Country of the show", example = "United States")
        String country,

        @JsonProperty("date_added")
        @Schema(description = "Date when the show was added", example = "December 18, 2020")
        String dateAdded,

        @Schema(description = "Description of the show")
        String description,

        @Schema(description = "Director of the show", example = "Azthony Mumingway")
        String director,

        @Schema(description = "Duration of the show", example = "60 min")
        String duration,

        @JsonProperty("listed_in")
        @Schema(description = "Categories the show is listed in", example = "Drama, Thriller, Documentary")
        String listedIn,

        @Schema(description = "Rating of the show", example = "TV-PG")
        String rating,

        @JsonProperty("release_year")
        @Schema(description = "Release year of the show", example = "2021")
        int releaseYear,

        @Schema(description = "Title of the show", example = "Yaya and qhe Dast Bzagon")
        String title,

        @Schema(description = "Type of the show", example = "Movie")
        String type,

        @Schema(description = "Platform of the show", example = "Sysney")
        String platform
) {
}

