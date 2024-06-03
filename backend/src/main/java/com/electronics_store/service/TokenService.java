package com.electronics_store.service;

import java.util.Map;

import com.electronics_store.model.dto.ApiResponse;

public interface TokenService {
    boolean isTokenExist(String token);

    boolean isRefreshTokenExist(String refreshToken);

    ApiResponse<?> getTokenByRefreshToken(Map<String, Object> data);
}
