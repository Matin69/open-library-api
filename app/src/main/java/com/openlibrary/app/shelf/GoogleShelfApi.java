package com.openlibrary.app.shelf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GoogleShelfApi {

    private final RestTemplate restTemplate;

    public GoogleShelfApi(@Qualifier("rest-google-books") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ShelvesCollectionResponse getMyLibraryShelves(String accessToken) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "Bearer " + accessToken);
        ResponseEntity<ShelvesCollectionResponse> response = restTemplate.exchange(
                UriComponentsBuilder.fromPath("/mylibrary/bookshelves").toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(requestHeaders),
                ShelvesCollectionResponse.class
        );
        return response.getBody();
    }
}
