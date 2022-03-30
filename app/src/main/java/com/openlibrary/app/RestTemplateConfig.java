package com.openlibrary.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfig {

    @Bean(name = "rest-google-books")
    public RestTemplate restTemplateGoogleBooks(RestTemplateBuilder builder,
                                                GoogleApisErrorHandler responseErrorHandler,
                                                @Value("${google.apis.books.url}") String baseUrl) {
        return getBasicBuilder(builder, responseErrorHandler)
                .uriTemplateHandler(new DefaultUriBuilderFactory(baseUrl))
                .build();
    }

    @Bean(name = "rest-google-auth")
    public RestTemplate restTemplateGoogleAuth(RestTemplateBuilder builder,
                                               GoogleApisErrorHandler responseErrorHandler) {
        return getBasicBuilder(builder, responseErrorHandler)
                .build();
    }

    private RestTemplateBuilder getBasicBuilder(RestTemplateBuilder builder, GoogleApisErrorHandler responseErrorHandler) {
        return builder.errorHandler(responseErrorHandler);
    }
}
