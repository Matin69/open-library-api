package com.openlibrary.app.book;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class GoogleVolumesApi {

    @Value("${google.apis.books.key}")
    private String key;

    private final RestTemplate restTemplate;

    public GoogleVolumesApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public VolumesCollectionResponse list(@NonNull String query,
                                          @Nullable String filter,
                                          @Nullable Integer startIndex,
                                          @Nullable Integer maxResult,
                                          @Nullable String projection) {
        ResponseEntity<VolumesCollectionResponse> response = restTemplate.getForEntity(
                UriComponentsBuilder.fromPath("/volumes")
                        .queryParam("q", query)
                        .queryParamIfPresent("filter", Optional.ofNullable(filter))
                        .queryParamIfPresent("projection", Optional.ofNullable(projection))
                        .queryParamIfPresent("startIndex", Optional.ofNullable(startIndex))
                        .queryParamIfPresent("maxResult", Optional.ofNullable(maxResult))
                        .queryParam("key", key)
                        .build()
                        .toUri()
                        .toString(),
                VolumesCollectionResponse.class
        );
        return response.getBody();
    }

    public VolumeResponse get(@NonNull String volumeId) {
        ResponseEntity<VolumeResponse> response = restTemplate.getForEntity(
                UriComponentsBuilder.fromPath("/volumes/{id}")
                        .queryParam("key", key)
                        .build(volumeId)
                        .toString(),
                VolumeResponse.class
        );
        return response.getBody();
    }
}
