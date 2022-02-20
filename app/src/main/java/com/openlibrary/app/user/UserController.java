package com.openlibrary.app.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @PostMapping
    ResponseEntity<?> create() {
        // Create a user
        return null;
    }

    @GetMapping
    ResponseEntity<?> list() {
        // List all users
        return null;
    }

    @GetMapping(path = "/{userId}")
    ResponseEntity<?> get(@PathVariable String userId) {
        // Get a user
        return null;
    }
}
