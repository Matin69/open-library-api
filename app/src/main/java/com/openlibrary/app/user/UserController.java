package com.openlibrary.app.user;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    UserInfo create(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        return userService.create(userCreateRequest);
    }

    @GetMapping(path = "/{userId}")
    UserInfo get(@PathVariable Long userId) {
        return userService.get(userId);
    }

    @DeleteMapping(path = "/{userId}")
    void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
