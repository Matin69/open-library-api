package com.openlibrary.app.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GoogleVolumesApiTest {

    @Autowired
    GoogleVolumesApi googleVolumesApi;

    @Test
    void list() {
        VolumesCollectionResponse result = googleVolumesApi.list(
                "hamlet",
                null,
                0,
                0,
                null
        );
        assertNotEquals(0, result.getTotalItems());
        assertNotNull(result.getItems());
    }

    @Test
    void get() throws InterruptedException {

    }
}