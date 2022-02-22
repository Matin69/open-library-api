package com.openlibrary.app.book;

import com.openlibrary.app.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoogleBooksApiTest {

    @Autowired
    GoogleBooksApi googleBooksApi;

    @Test
    void list() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        List<Book> books = new ArrayList<>();
        googleBooksApi.list(
                "hamlet",
                null,
                0,
                20,
                null,
                book -> {
                    books.add(book);
                    countDownLatch.countDown();
                }
        );
        countDownLatch.await();
        assertNotEquals(0, books.size());
    }

    @Test
    void get() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        googleBooksApi.get(
                "S04tXAPxaikC",
                volume -> {
                    assertNotNull(volume);
                    assertNotNull(volume.getId());
                    assertNotNull(volume.getVolumeInfo());
                    assertNotNull(volume.getVolumeInfo().getTitle());
                    countDownLatch.countDown();
                },
                throwable -> {
                }
        );
        googleBooksApi.get(
                "zyTC0lF9jgY2",
                volumeResponse -> {
                },
                throwable -> {
                    assertInstanceOf(ResourceNotFoundException.class, throwable);
                    countDownLatch.countDown();
                }
        );
        countDownLatch.await();
    }
}