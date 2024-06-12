package com.electronics_store.service;

import jakarta.servlet.http.HttpServletRequest;

import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.auth.LoginDTO;
import com.electronics_store.model.dto.response.LoginResponseDTO;

public interface AuthenticationService {
    ApiResponse<LoginResponseDTO> login(LoginDTO loginDTO, HttpServletRequest request);
}
