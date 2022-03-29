package com.openlibrary.app.shelf;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShelvesController {

    private final ShelvesService shelvesService;

    public ShelvesController(ShelvesService shelvesService) {
        this.shelvesService = shelvesService;
    }

    @GetMapping("/mylibrary/bookshelves")
    public List<ShelfResponse> getUserPrivateShelves(Authentication authentication) {
        return shelvesService.getUserShelves((String) authentication.getCredentials());
    }
}
