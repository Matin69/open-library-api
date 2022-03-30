package com.openlibrary.app.security;

import com.openlibrary.app.GoogleUserInfoResponse;
import com.openlibrary.app.user.User;
import com.openlibrary.app.user.UserConverter;
import com.openlibrary.app.user.UserInfo;
import com.openlibrary.app.user.UserRepository;
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

    private final UserRepository userRepository;

    public AuthServiceImpl(GoogleAuthProperties googleAuthProperties, GoogleAuthApi googleAuthApi, UserRepository userRepository) {
        this.googleAuthProperties = googleAuthProperties;
        this.googleAuthApi = googleAuthApi;
        this.userRepository = userRepository;
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
    public UserInfo authenticate(String authCode) {
        TokenRequestResponse tokenResponse = googleAuthApi.token(authCode);
        GoogleUserInfoResponse userInfoResponse = googleAuthApi.userInfo(tokenResponse.getAccessToken());
        User user = userRepository.findByEmail(userInfoResponse.getEmail())
                .orElseGet(() ->
                        userRepository.save(new User(
                                userInfoResponse.getName(),
                                userInfoResponse.getEmail(),
                                userInfoResponse.getGender(),
                                userInfoResponse.getFamilyName(),
                                null,
                                tokenResponse.getRefreshToken()
                        ))
                );
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                user.getId(),
                tokenResponse.getAccessToken(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        ));
        return UserConverter.toUserInfo(user);
    }
}
