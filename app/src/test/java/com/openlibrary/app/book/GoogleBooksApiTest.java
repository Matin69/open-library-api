package com.openlibrary.app.book;

import com.openlibrary.app.BadRequestException;
import com.openlibrary.app.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoogleBooksApiTest {

    @Autowired
    GoogleBooksApi googleBooksApi;

    @Test
    void list() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        googleBooksApi.list(
                "hamlet",
                null,
                0,
                20,
                null,
                volumesCollectionResponse -> {
                    assertNotEquals(0, volumesCollectionResponse.getTotalItems());
                    assertNotNull(volumesCollectionResponse.getItems());
                    assertNotEquals(0, volumesCollectionResponse.getItems().size());
                    System.out.println(volumesCollectionResponse);
                    countDownLatch.countDown();
                },
                throwable -> {
                }
        );
        googleBooksApi.list(
                "",
                null,
                0,
                20,
                null,
                volumesCollectionResponse -> {
                },
                throwable -> {
                    assertInstanceOf(BadRequestException.class, throwable);
                    countDownLatch.countDown();
                }
        );
        countDownLatch.await();
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