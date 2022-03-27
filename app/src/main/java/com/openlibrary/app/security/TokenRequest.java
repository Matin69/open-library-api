package com.openlibrary.app.security;

import lombok.Data;

@Data
public class TokenRequest {

    private String clientId;

    private String clientSecret;

    private String code;

    private String grantType;

    private String redirectUri;

    public TokenRequest(String clientId, String clientSecret, String code, String grantType, String redirectUri) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
        this.grantType = grantType;
        this.redirectUri = redirectUri;
    }
}
