package com.openlibrary.app.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    @NotEmpty
    private String name;

    @Email
    private String email;

    @Size(min = 6, max = 16)
    private String password;
}
