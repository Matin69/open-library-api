package com.openlibrary.app;

import com.openlibrary.app.security.GoogleAuthProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@EnableConfigurationProperties(GoogleAuthProperties.class)
@SpringBootApplication
@Configuration
@EnableAsync
public class Application {

    @Value("${google.apis.books.url}")
    private String baseUrl;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean(name = "rest-google-books")
    public RestTemplate restTemplateGoogleBooks(RestTemplateBuilder builder, GoogleApisErrorHandler responseErrorHandler) {
        return builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(baseUrl))
                .errorHandler(responseErrorHandler)
                .build();
    }

    @Bean(name = "rest-google-auth")
    public RestTemplate restTemplateGoogleAuth(RestTemplateBuilder builder,
                                               GoogleApisErrorHandler responseErrorHandler) {
        return builder
                .errorHandler(responseErrorHandler)
                .build();
    }
}
