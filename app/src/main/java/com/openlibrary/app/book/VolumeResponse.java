package com.openlibrary.app.book;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class VolumeResponse {

    private String id;

    private Info volumeInfo;

    @Data
    static class Info {

        private String title;

        private Set<String> authors;

        private String publisher;

        private Date publishedDate;

        private String description;

        private int pageCount;

        private String language;
    }
}
