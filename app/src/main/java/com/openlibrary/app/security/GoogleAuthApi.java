package com.openlibrary.app.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleAuthApi {

    private final RestTemplate restTemplate;

    private final GoogleAuthProperties googleAuthProperties;

    public GoogleAuthApi(@Qualifier("rest-google-auth") RestTemplate restTemplate, GoogleAuthProperties googleAuthProperties) {
        this.restTemplate = restTemplate;
        this.googleAuthProperties = googleAuthProperties;
    }

    public TokenRequestResponse token(String code) {
        TokenRequest tokenRequest = new TokenRequest(
                googleAuthProperties.getClientId(),
                googleAuthProperties.getClientSecret(),
                code,
                googleAuthProperties.getGrantType(),
                googleAuthProperties.getRedirectUri()
        );
        ResponseEntity<TokenRequestResponse> response = restTemplate.postForEntity(
                googleAuthProperties.getTokenUrl(),
                tokenRequest,
                TokenRequestResponse.class
        );
        return response.getBody();
    }
}
