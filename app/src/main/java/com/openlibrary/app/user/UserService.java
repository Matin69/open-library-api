package com.openlibrary.app.user;

public interface UserService {

    UserInfo create(UserCreateRequest userCreateRequest);

    UserInfo get(Long userId);

    void delete(Long userId);
}
