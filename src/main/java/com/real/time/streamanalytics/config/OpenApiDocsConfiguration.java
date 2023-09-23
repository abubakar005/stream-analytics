package com.real.time.streamanalytics.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Open API Docs configuration and details that will be visible on the UI
 * */

@Configuration
public class OpenApiDocsConfiguration {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Stream Analytics Application APIs Docs")
                        .description("Rest APIs for Stream Analytics Application")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Abu Bakar")
                                .url("https://github.com/Sytac-DevCase/Java-abubakar005")
                                .email("abubakar.cs@gmail.com"))
                        .termsOfService("TOC")
                        .license(new License().name("License").url("#"))
                );
    }
}