package com.electronics_store.controller;

import com.electronics_store.model.dto.request.AccountDTO;
import com.electronics_store.service.AccountService;
import com.electronics_store.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountDTO accountDTO, HttpServletRequest request){
        return accountService.login(accountDTO,request);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerByUser(@Valid @RequestBody AccountDTO accountDTO){
        return accountService.createAccountByUser(accountDTO);
    }

    @PostMapping("/token/refresh-token")
    private ResponseEntity<?> refreshToken(@RequestBody Map<String,Object> data){
        return tokenService.getTokenByRefreshToken(data);
    }


    @GetMapping
    public String getAccount(){
        return "success";
    }
}
