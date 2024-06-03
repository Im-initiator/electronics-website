package com.electronics_store.service;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.account.CreateAccountByUserDTORequest;

public interface AccountService {
    Optional<CreateAccountByUserDTORequest> findByUserName(String userName);

    ApiResponse<?> createAccountByUser(CreateAccountByUserDTORequest createAccountByUserDTORequest);

    ApiResponse<?> login(CreateAccountByUserDTORequest createAccountByUserDTORequest, HttpServletRequest request);
}
