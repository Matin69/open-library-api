package com.openlibrary.app.shelf;

import com.openlibrary.app.GoogleCollectionResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
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

    public GoogleCollectionResponse<ShelfResponse> getMyLibraryShelves(String accessToken) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "Bearer " + accessToken);
        ResponseEntity<GoogleCollectionResponse<ShelfResponse>> response = restTemplate.exchange(
                UriComponentsBuilder.fromPath("/mylibrary/bookshelves").toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(requestHeaders),
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }
}
