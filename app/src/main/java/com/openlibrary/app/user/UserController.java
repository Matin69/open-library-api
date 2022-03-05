package com.openlibrary.app.user;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping
    UserInfo create(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        return userServiceImpl.create(userCreateRequest);
    }

    @GetMapping(path = "/{userId}")
    UserInfo get(@PathVariable Long userId) {
        return userServiceImpl.get(userId);
    }

    @DeleteMapping(path = "/{userId}")
    void delete(@PathVariable Long userId) {
        userServiceImpl.delete(userId);
    }
}
