package com.openlibrary.app.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public Set<Book> list(@RequestParam String query,
                          @RequestParam(required = false) String filter,
                          @RequestParam(required = false) Integer startIndex,
                          @RequestParam(required = false) Integer maxResult,
                          @RequestParam(required = false) String projection) {
        return bookService.list(
                query,
                filter,
                startIndex,
                maxResult,
                projection
        );
    }

    @GetMapping(path = "/books/{id}")
    public Book get(@PathVariable String id) {
        return bookService.get(id);
    }
}
