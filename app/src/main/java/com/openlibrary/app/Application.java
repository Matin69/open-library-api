package com.openlibrary.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@EnableWebSecurity
@SpringBootApplication
@Configuration
@EnableAsync
public class Application {

    @Value("${google.apis.books.url}")
    private String baseUrl;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, ResponseErrorHandlerImpl responseErrorHandler) {
        return builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(baseUrl))
                .errorHandler(responseErrorHandler)
                .build();
    }
}
