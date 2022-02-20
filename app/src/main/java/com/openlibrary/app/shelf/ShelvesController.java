package com.openlibrary.app.shelf;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users/{userId}/bookshelves")
public class ShelvesController {

    @GetMapping
    ResponseEntity<?> list(@PathVariable String userId) {
        // list all shelves
        return null;
    }

    @GetMapping("/{shelfId}")
    ResponseEntity<?> get(@PathVariable String userId, @PathVariable String shelfId) {
        // get a shelf
        return null;
    }

    @GetMapping(path = "/{bookShelfId}/books")
    ResponseEntity<?> getShelfBooks(@PathVariable String userId, @PathVariable String bookShelfId) {
        // get books of a shelf
        return null;
    }
}
