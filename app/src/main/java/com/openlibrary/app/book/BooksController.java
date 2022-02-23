package com.openlibrary.app.book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Mono<VolumesCollectionResponse> list(@RequestParam String query,
                                                @RequestParam(required = false) String filter,
                                                @RequestParam(required = false) Integer startIndex,
                                                @RequestParam(required = false) Integer maxResult,
                                                @RequestParam(required = false) String projection) {
        return bookService.list(query, filter, 0, 0, projection);
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<?> get(@PathVariable String id) {
        // get a book
        return null;
    }
}
