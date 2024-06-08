package com.electronics_store.model.dto.request.token;

import java.time.LocalDateTime;

import com.electronics_store.enums.TokenType;

import lombok.Data;

@Data
public class TokenDTO {
    private Long id;
    private String accessToken;
    private String refreshToken;
    private boolean expired;
    private boolean revoked;
    private TokenType tokenType;
    private LocalDateTime createDate;
}
