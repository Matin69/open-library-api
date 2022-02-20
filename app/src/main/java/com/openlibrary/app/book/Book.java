package com.openlibrary.app.book;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class Book {

    private String id;

    private String title;

    private Set<String> authors;

    private String publisher;

    private Date publishedDate;

    private String description;

    private int pageCount;

    private String language;

    public Book(String id, String title, Set<String> authors, String publisher, Date publishedDate, String description, int pageCount, String language) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = pageCount;
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
