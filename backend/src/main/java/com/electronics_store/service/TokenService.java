package com.electronics_store.service;

import java.util.Map;

import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.token.UpdateTokenByAdminDTO;

public interface TokenService {
    boolean isTokenExist(String token);

    boolean isRefreshTokenExist(String refreshToken);

    ApiResponse<?> getTokenByRefreshToken(Map<String, Object> data);

    ApiResponse<?> getTokenByAdmin(Long id);

    ApiResponse<?> updateTokenByAdmin(Long userId, Long tokenId, UpdateTokenByAdminDTO updateTokenByAdminDTO);
}
