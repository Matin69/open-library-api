package com.openlibrary.app.security;

import com.openlibrary.app.user.UserInfo;

public interface AuthService {

    String getAuthServerUri();

    UserInfo authenticate(String authCode);
}
