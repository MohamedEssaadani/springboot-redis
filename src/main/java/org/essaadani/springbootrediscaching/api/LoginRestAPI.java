package org.essaadani.springbootrediscaching.api;

import org.essaadani.springbootrediscaching.entities.UserEntity;
import org.essaadani.springbootrediscaching.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/login")
public class LoginRestAPI {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserEntity user) {
        // get username & password
        String username = user.getUsername();
        String password = user.getPassword();

        // check for non null values
        if (Objects.isNull(username) || Objects.isNull(password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }

        // login user & get the token
        String token = loginService.loginAndGetToken(username, password);

        if (token != null)
            return ResponseEntity.ok("Login Successful, Token : " + token);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
    }
}
