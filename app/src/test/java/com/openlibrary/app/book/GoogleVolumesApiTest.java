package com.openlibrary.app.book;

import com.openlibrary.app.BadRequestException;
import com.openlibrary.app.GoogleCollectionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoogleVolumesApiTest {

    @Autowired
    GoogleVolumesApi googleVolumesApi;

    @Test
    void list() {
        GoogleCollectionResponse<VolumeResponse> result = googleVolumesApi.list(
                "hamlet",
                null,
                0,
                0,
                null
        );
        assertNotEquals(0, result.getTotalItems());
        assertNotNull(result.getItems());
        assertThrows(BadRequestException.class, () -> {
            googleVolumesApi.list(
                    "",
                    null,
                    0,
                    0,
                    null
            );
        });
    }

    @Test
    void get() {
        VolumeResponse volumeResponse = googleVolumesApi.get("S04tXAPxaikC");
        assertNotNull(volumeResponse);
        assertNotNull(volumeResponse.getId());
        assertNotNull(volumeResponse.getVolumeInfo());
    }
}