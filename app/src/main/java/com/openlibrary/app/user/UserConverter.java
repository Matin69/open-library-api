package com.openlibrary.app.user;

public class UserConverter {

    public static UserInfo fromUser(User user) {
        return new UserInfo(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getGender(),
                user.getFamilyName()
        );
    }

    public static User toUser(UserCreateRequest userCreateRequest) {
        return new User(
                userCreateRequest.getName(),
                userCreateRequest.getEmail(),
                userCreateRequest.getPassword()
        );
    }
}
