package com.openlibrary.app.book;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final GoogleVolumesApi volumesApi;

    private final BookConverter bookConverter;

    public BookService(GoogleVolumesApi volumesApi, BookConverter bookConverter) {
        this.volumesApi = volumesApi;
        this.bookConverter = bookConverter;
    }

    public CompletableFuture<Set<Book>> list(String query,
                                             String filter,
                                             Integer startIndex,
                                             Integer maxResult,
                                             String projection) {
        VolumesCollectionResponse response = volumesApi.list(query, filter, startIndex, maxResult, projection);
        Set<Book> books = response.getItems().stream()
                .map(bookConverter::toBook)
                .collect(Collectors.toSet());
        return CompletableFuture.completedFuture(books);
    }
}
