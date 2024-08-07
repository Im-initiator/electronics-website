package com.electronics_store.controller;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.electronics_store.model.dto.request.account.CreateAccountByUserRequestDTO;
import com.electronics_store.model.dto.request.auth.LoginDTO;
import com.electronics_store.service.AccountService;
import com.electronics_store.service.AuthenticationService;
import com.electronics_store.service.TokenService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "_auth", description = "Authentication")
@RestController
public class AuthController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        return ResponseEntity.ok().body(authenticationService.login(loginDTO, request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerByUser(
            @Valid @RequestBody CreateAccountByUserRequestDTO createAccountByUserRequestDTO) {
        return ResponseEntity.ok().body(accountService.createAccountByUser(createAccountByUserRequestDTO));
    }

    @PostMapping("/token/refresh-token")
    private ResponseEntity<?> refreshToken(@RequestBody Map<String, Object> data) {
        return ResponseEntity.ok().body(tokenService.getTokenByRefreshToken(data));
    }
}
