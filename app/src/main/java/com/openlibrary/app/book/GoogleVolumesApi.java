package com.openlibrary.app.book;

import com.openlibrary.app.BadRequestException;
import com.openlibrary.app.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Consumer;

@Service
public class GoogleVolumesApi {

    @Value("${google.apis.books.url}")
    private String baseUrl;

    @Value("${google.apis.books.key}")
    private String key;

    public void list(@NonNull String query,
                     @Nullable String filter,
                     @Nullable int startIndex,
                     @Nullable int maxResult,
                     @Nullable String projection,
                     @NonNull Consumer<VolumesCollectionResponse> volumeConsumer,
                     @NonNull Consumer<Throwable> onError) {
        Mono<VolumesCollectionResponse> booksFlux = WebClient.create(baseUrl)
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
                .onStatus(
                        httpStatus -> httpStatus == HttpStatus.BAD_REQUEST,
                        clientResponse -> Mono.error(new BadRequestException())
                )
                .bodyToMono(VolumesCollectionResponse.class)
                .doOnError(onError);
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
