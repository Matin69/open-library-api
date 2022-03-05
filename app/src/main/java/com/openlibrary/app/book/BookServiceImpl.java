package com.openlibrary.app.book;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final GoogleVolumesApi volumesApi;

    private final BookConverter bookConverter;

    public BookServiceImpl(GoogleVolumesApi volumesApi, BookConverter bookConverter) {
        this.volumesApi = volumesApi;
        this.bookConverter = bookConverter;
    }

    @Override
    public Set<Book> list(String query,
                          String filter,
                          Integer startIndex,
                          Integer maxResult,
                          String projection) {
        VolumesCollectionResponse response = volumesApi.list(query, filter, startIndex, maxResult, projection);
        return response.getItems().stream()
                .map(bookConverter::toBook)
                .collect(Collectors.toSet());
    }

    @Override
    public Book get(@NonNull String bookId) {
        VolumeResponse response = volumesApi.get(bookId);
        return bookConverter.toBook(response);
    }
}
