package com.bashayer.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI userManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Management API")
                        .description("Spring Boot User Management API with CRUD, search, validation, DTOs, service layer, and automated testing.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Bashayer")
                                .email("bashayer@example.com")));
    }
}