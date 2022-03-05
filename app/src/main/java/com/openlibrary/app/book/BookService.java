package com.openlibrary.app.book;

import org.springframework.lang.NonNull;

import java.util.Set;

public interface BookService {

    Set<Book> list(String query,
                   String filter,
                   Integer startIndex,
                   Integer maxResult,
                   String projection);

    Book get(@NonNull String bookId);
}
