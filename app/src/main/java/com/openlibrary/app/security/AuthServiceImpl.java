package com.openlibrary.app.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    private final GoogleAuthProperties googleAuthProperties;

    private final GoogleAuthApi googleAuthApi;

    public AuthServiceImpl(GoogleAuthProperties googleAuthProperties, GoogleAuthApi googleAuthApi) {
        this.googleAuthProperties = googleAuthProperties;
        this.googleAuthApi = googleAuthApi;
    }

    @Override
    public String getAuthServerUri() {
        return UriComponentsBuilder.fromHttpUrl(googleAuthProperties.getUrl())
                .queryParam("client_id", googleAuthProperties.getClientId())
                .queryParam("redirect_uri", googleAuthProperties.getRedirectUri())
                .queryParam("response_type", googleAuthProperties.getResponseType())
                .queryParam("scope", googleAuthProperties.getScope())
                .build()
                .toString();
    }

    @Override
    public boolean authenticate(String authCode) {
        TokenRequestResponse response = googleAuthApi.token(authCode);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                response.getAccessToken(),
                null,
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        ));
        return true;
    }
}
