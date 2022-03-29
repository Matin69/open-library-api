package com.openlibrary.app.security;

import com.openlibrary.app.user.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path = "/auth/login")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect(authService.getAuthServerUri());
    }

    @GetMapping(path = "/oauth2/callback")
    public ResponseEntity<UserInfo> loginCallback(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String error) {
        if (error != null) {
            throw new IllegalStateException("Google auth error : " + error);
        }
        UserInfo authUser = authService.authenticate(code);
        if (authUser != null) {
            return ResponseEntity.ok(authUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
