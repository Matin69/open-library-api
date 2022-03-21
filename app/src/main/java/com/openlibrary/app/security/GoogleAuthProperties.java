package com.openlibrary.app.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "google.apis.auth")
public class GoogleAuthProperties {

    private final String url;

    private final String clientId;

    private final String redirectUri;

    private final String responseType;

    private final String scope;

    public GoogleAuthProperties(String url, String clientId, String redirectUri, String responseType, String scope) {
        this.url = url;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
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
}
