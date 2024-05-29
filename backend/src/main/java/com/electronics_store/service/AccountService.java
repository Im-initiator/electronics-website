package com.electronics_store.service;

import java.util.Optional;

import com.electronics_store.model.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.electronics_store.model.dto.request.account.CreateAccountByUserDTO;

public interface AccountService {
    Optional<CreateAccountByUserDTO> findByUserName(String userName);

    ApiResponse<?> createAccountByUser(CreateAccountByUserDTO createAccountByUserDTO);

    ApiResponse<?> login(CreateAccountByUserDTO createAccountByUserDTO, HttpServletRequest request);
}
