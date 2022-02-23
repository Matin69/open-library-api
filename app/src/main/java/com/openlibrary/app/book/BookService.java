package com.openlibrary.app.book;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookService {

    private final GoogleVolumesApi volumesApi;

    public BookService(GoogleVolumesApi volumesApi) {
        this.volumesApi = volumesApi;
    }

    public Mono<VolumesCollectionResponse> list(String query,
                                                String filter,
                                                int startIndex,
                                                int maxResult,
                                                String projection) {
        Set<Book> books = new HashSet<>();
        Mono<VolumesCollectionResponse> listMono = volumesApi.list(
                query,
                filter,
                startIndex,
                maxResult,
                projection,
                volumesCollectionResponse -> {
                    if (volumesCollectionResponse != null) {
                        volumesCollectionResponse.getItems()
                                .forEach(volumeResponse -> books.add(new Book()));
                    }
                },
                throwable -> {
                }
        );
        return listMono;
    }

    public void get() {

    }
}
