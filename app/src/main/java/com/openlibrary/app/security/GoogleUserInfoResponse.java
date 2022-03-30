package com.openlibrary.app.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GoogleUserInfoResponse {

    private String id;

    private String name;

    @JsonProperty("family_name")
    private String familyName;

    private String gender;

    private String email;
}
