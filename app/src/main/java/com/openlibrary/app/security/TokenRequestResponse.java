package com.openlibrary.app.security;

import lombok.Data;

@Data
public class TokenRequestResponse {

    private String accessToken;

    private String tokenType;

    private String scope;

    private int expiresIn;

    private String refreshToken;
}
