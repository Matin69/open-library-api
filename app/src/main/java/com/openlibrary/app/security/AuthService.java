package com.openlibrary.app.security;

public interface AuthService {

    String getAuthServerUri();

    boolean authenticate(String authCode);
}
