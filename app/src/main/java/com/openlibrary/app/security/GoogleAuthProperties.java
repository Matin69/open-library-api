package com.openlibrary.app.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "google.apis.auth")
public class GoogleAuthProperties {

    private final String url;

    private final String tokenUrl;

    private final String clientId;

    private final String clientSecret;

    private final String redirectUri;

    private final String tokenRedirectUri;

    private final String grantType;

    private final String responseType;

    private final String scope;

    public GoogleAuthProperties(String url, String tokenUrl, String clientId, String clientSecret, String redirectUri, String tokenRedirectUri, String grantType, String responseType, String scope) {
        this.url = url;
        this.tokenUrl = tokenUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.tokenRedirectUri = tokenRedirectUri;
        this.grantType = grantType;
        this.responseType = responseType;
        this.scope = scope;
    }

    public String getUrl() {
        return url;
    }

    public String getClientId() {
        return clientId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getResponseType() {
        return responseType;
    }

    public String getScope() {
        return scope;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getTokenRedirectUri() {
        return tokenRedirectUri;
    }
}
