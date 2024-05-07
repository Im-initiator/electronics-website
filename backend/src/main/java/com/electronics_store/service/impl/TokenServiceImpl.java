package com.electronics_store.service.impl;

import com.electronics_store.auth.userDetails.CustomUserDetails;
import com.electronics_store.auth.userDetails.CustomUserDetailsService;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.LoginResponseDTO;
import com.electronics_store.model.entity.TokenEntity;
import com.electronics_store.repository.TokenRepository;
import com.electronics_store.service.TokenService;
import com.electronics_store.service.jwt.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {


    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;


    @Override
    public boolean isTokenExist(String token) {
       return tokenRepository.findByAccessToken(token)
               .map( t -> !t.isExpired()&& !t.isRevoked())
               .orElse(false);
    }

    @Override
    public boolean isRefreshTokenExist(String refreshToken) {
        return tokenRepository.findByRefreshToken(refreshToken)
                .map( t -> !t.isExpired()&& !t.isRevoked())
                .orElse(false);
    }

    @Override
    @Transactional
    public ResponseEntity<?> getTokenByRefreshToken(Map<String, Object> data) {
        String refreshToken = (String) data.get("refresh-token");
        String userName;
        try {
           userName = jwtService.extractUsername(refreshToken);
        }catch (SignatureException | MalformedJwtException exception){
            throw new CustomRuntimeException(ErrorSystem.REFRESH_TOKEN_IS_CORRECT);
        }catch (IllegalArgumentException e){
            throw new CustomRuntimeException(ErrorSystem.DATA_IS_WRONG_FORMAT);
        }catch (ExpiredJwtException e){
            throw new CustomRuntimeException(ErrorSystem.REFRESH_JWT_TOKEN_EXPIRED);
        }
        if (userName!=null){
            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(userName);
            boolean isRefreshTokenExist = this.isRefreshTokenExist(refreshToken);
            if (jwtService.isRefreshTokenValid(refreshToken,userDetails)&&isRefreshTokenExist){
                String accessToken = jwtService.generateToken(userDetails);
                //handle exception
                TokenEntity token = tokenRepository.findByRefreshToken(refreshToken).orElseThrow();
                token.setAccessToken(accessToken);
                tokenRepository.save(token);
                LoginResponseDTO responseDTO = LoginResponseDTO.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                ApiResponse<?> response = ApiResponse.builder()
                        .code(100)
                        .message("get access token successful")
                        .data(responseDTO)
                        .build();
                return ResponseEntity.ok(response);
            }
        }
        throw new CustomRuntimeException(ErrorSystem.REFRESH_TOKEN_IS_CORRECT);
    }
}
