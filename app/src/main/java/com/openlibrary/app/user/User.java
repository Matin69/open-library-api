package com.openlibrary.app.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private String gender;

    private String familyName;

    private String password;

    private String refreshToken;

    public User(String name, String email, String gender, String familyName, String password, String refreshToken) {
        this(name, email, password);
        this.gender = gender;
        this.familyName = familyName;
        this.password = password;
        this.refreshToken = refreshToken;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
