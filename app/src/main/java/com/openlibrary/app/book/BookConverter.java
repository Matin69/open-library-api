package com.openlibrary.app.book;

import org.springframework.stereotype.Component;

@Component
public class BookConverter {

    public Book toBook(VolumeResponse volumeResponse) {
        return new Book(
                volumeResponse.getId(),
                volumeResponse.getVolumeInfo().getTitle(),
                volumeResponse.getVolumeInfo().getAuthors(),
                volumeResponse.getVolumeInfo().getPublisher(),
                volumeResponse.getVolumeInfo().getPublishedDate(),
                volumeResponse.getVolumeInfo().getDescription(),
                volumeResponse.getVolumeInfo().getPageCount(),
                volumeResponse.getVolumeInfo().getLanguage()
        );
    }
}
