package com.openlibrary.app.user;

import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserInfo fromUser(User user) {
        return new UserInfo(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getGender(),
                user.getFamilyName()
        );
    }

    public User toUser(UserCreateRequest userCreateRequest) {
        return new User(
                userCreateRequest.getName(),
                userCreateRequest.getEmail(),
                userCreateRequest.getPassword()
        );
    }
}
