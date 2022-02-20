package com.openlibrary.app.shelf;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class Shelf {

    private String id;

    private String title;

    private ShelfAccessType shelfAccessType;

    private Date creationTime;

    private long booksCount;

    public Shelf(String id, String title, ShelfAccessType shelfAccessType, Date creationTime, long booksCount) {
        this.id = id;
        this.title = title;
        this.shelfAccessType = shelfAccessType;
        this.creationTime = creationTime;
        this.booksCount = booksCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelf shelf = (Shelf) o;
        return id.equals(shelf.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
