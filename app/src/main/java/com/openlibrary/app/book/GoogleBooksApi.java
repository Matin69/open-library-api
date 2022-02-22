package com.openlibrary.app.book;

import com.openlibrary.app.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Consumer;

@Service
public class GoogleBooksApi {

    @Value("${google.apis.books.url}")
    private String baseUrl;

    @Value("${google.apis.books.key}")
    private String key;

    public void list(String query,
                     String filter,
                     int startIndex,
                     int maxResult,
                     String projection,
                     Consumer<VolumeResponse> volumeConsumer) {
        Mono<VolumeResponse> booksFlux = WebClient.create(baseUrl)
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path("/volumes")
                                .queryParam("q", query)
                                .queryParamIfPresent("filter", Optional.ofNullable(filter))
                                .queryParamIfPresent("projection", Optional.ofNullable(projection))
                                .queryParamIfPresent("startIndex", Optional.of(startIndex))
                                .queryParamIfPresent("maxResult", Optional.of(maxResult))
                                .queryParam("key", key)
                                .build()
                )
                .retrieve()
                .bodyToMono(VolumeResponse.class);
        booksFlux.subscribe(volumeConsumer);
    }

    public void get(@NonNull String volumeId,
                    @NonNull Consumer<VolumeResponse> volumeSubscriber,
                    @NonNull Consumer<Throwable> onError) {
        Mono<VolumeResponse> volumeResponseMono = WebClient.create(baseUrl)
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path("/volumes/{volumeId}")
                                .queryParam("key", key)
                                .build(volumeId)
                )
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus == HttpStatus.NOT_FOUND,
                        clientResponse -> Mono.error(new ResourceNotFoundException())
                )
                .bodyToMono(VolumeResponse.class)
                .doOnError(onError);
        volumeResponseMono.subscribe(volumeSubscriber);
    }
}
