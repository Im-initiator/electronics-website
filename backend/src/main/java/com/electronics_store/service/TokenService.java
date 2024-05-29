package com.electronics_store.service;

import java.util.Map;

import com.electronics_store.model.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface TokenService {
    boolean isTokenExist(String token);

    boolean isRefreshTokenExist(String refreshToken);

    ApiResponse<?> getTokenByRefreshToken(Map<String, Object> data);
}
