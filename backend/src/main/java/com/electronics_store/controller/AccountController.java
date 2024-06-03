package com.electronics_store.controller;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.electronics_store.model.dto.request.account.CreateAccountByUserDTORequest;
import com.electronics_store.service.AccountService;
import com.electronics_store.service.TokenService;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody CreateAccountByUserDTORequest createAccountByUserDTORequest, HttpServletRequest request) {
        return ResponseEntity.ok().body(accountService.login(createAccountByUserDTORequest, request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerByUser(
            @Valid @RequestBody CreateAccountByUserDTORequest createAccountByUserDTORequest) {
        return ResponseEntity.ok().body(accountService.createAccountByUser(createAccountByUserDTORequest));
    }

    @PostMapping("/token/refresh-token")
    private ResponseEntity<?> refreshToken(@RequestBody Map<String, Object> data) {
        return ResponseEntity.ok().body(tokenService.getTokenByRefreshToken(data));
    }

    @GetMapping
    public String getAccount() {
        return "success";
    }
}
