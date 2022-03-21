package com.openlibrary.app.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "/auth/login")
public class AuthController {

    private final GoogleAuthProperties googleAuthProperties;

    public AuthController(GoogleAuthProperties googleAuthProperties) {
        this.googleAuthProperties = googleAuthProperties;
    }

    @GetMapping
    public void login(HttpServletResponse response) throws IOException {
        String googleAuthRedirect = UriComponentsBuilder.fromHttpUrl(googleAuthProperties.getUrl())
                .queryParam("client_id", googleAuthProperties.getClientId())
                .queryParam("redirect_uri", googleAuthProperties.getRedirectUri())
                .queryParam("response_type", googleAuthProperties.getResponseType())
                .queryParam("scope", googleAuthProperties.getScope())
                .build()
                .toString();
        response.sendRedirect(googleAuthRedirect);
    }
}
