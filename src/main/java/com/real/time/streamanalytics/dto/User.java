package com.real.time.streamanalytics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Details of a user")
public record User(
        @Schema(description = "ID of the user", example = "12345")
        long id,

        @JsonProperty("date_of_birth")
        @Schema(description = "Date of birth of the user", example = "28/07/1986")
        String dateOfBirth,

        @Schema(description = "Email address of the user", example = "john.doe@example.com")
        String email,

        @JsonProperty("first_name")
        @Schema(description = "First name of the user", example = "John")
        String firstName,

        @Schema(description = "Gender of the user", example = "Male")
        String gender,

        @JsonProperty("ip_address")
        @Schema(description = "IP address of the user", example = "192.168.0.1")
        String ipAddress,

        @Schema(description = "Country code of the user", example = "US")
        String country,

        @JsonProperty("last_name")
        @Schema(description = "Last name of the user", example = "Doe")
        String lastName
) {
}

