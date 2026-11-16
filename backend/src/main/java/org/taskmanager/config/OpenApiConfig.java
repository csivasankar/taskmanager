package org.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI taskManagerOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Task Manager REST API")
                        .description("REST APIs for creating, retrieving, "
                                + "and managing tasks.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Task Manager Support"))
                        .license(new License()
                                .name("Apache 2.0")));
    }
}