package com.electronics_store.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Objects;

public interface TokenService {
    boolean isTokenExist(String token);
    boolean isRefreshTokenExist(String refreshToken);

    ResponseEntity<?> getTokenByRefreshToken(Map<String, Object> data);
}
