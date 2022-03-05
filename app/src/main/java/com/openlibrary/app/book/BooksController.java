package com.openlibrary.app.book;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/books")
public class BooksController {

    private final BookServiceImpl bookServiceImpl;

    public BooksController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @GetMapping
    public Set<Book> list(@RequestParam String query,
                          @RequestParam(required = false) String filter,
                          @RequestParam(required = false) Integer startIndex,
                          @RequestParam(required = false) Integer maxResult,
                          @RequestParam(required = false) String projection) {
        return bookServiceImpl.list(
                query,
                filter,
                startIndex,
                maxResult,
                projection
        );
    }

    @GetMapping(path = "/{id}")
    Book get(@PathVariable String id) {
        return bookServiceImpl.get(id);
    }
}
