package com.electronics_store.service.impl;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import com.electronics_store.auth.userDetails.CustomUserDetails;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.auth.LoginDTO;
import com.electronics_store.model.dto.response.LoginResponseDTO;
import com.electronics_store.model.entity.TokenEntity;
import com.electronics_store.repository.TokenRepository;
import com.electronics_store.service.AuthenticationService;
import com.electronics_store.service.jwt.JwtService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthenticationServiceImpl implements AuthenticationService {

    AuthenticationManager authenticationManager;
    JwtService jwtService;
    TokenRepository tokenRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResponse<LoginResponseDTO> login(LoginDTO loginDTO, HttpServletRequest request) {
        // information web
        WebAuthenticationDetails webAuthenticationDetailsSource =
                new WebAuthenticationDetailsSource().buildDetails(request);
        // create credentials
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword());
        // authenticate
        usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken)
                authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        usernamePasswordAuthenticationToken.setDetails(webAuthenticationDetailsSource);
        // check authentication
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) usernamePasswordAuthenticationToken.getPrincipal();
            String accessToken = jwtService.generateToken(userDetails);
            String refreshToken = jwtService.generateRefreshToken(userDetails);
            TokenEntity token = TokenEntity.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .account(userDetails.getAccountEntity())
                    .build();
            int countToken =
                    tokenRepository.countAllByAccount_Id(userDetails.getId()).orElse(0);
            if (countToken >= 3) {
                List<TokenEntity> tokens =
                        tokenRepository.findAllByAccountIdOrderByCreateDateOffsetTwo(userDetails.getId());
                tokenRepository.deleteAll(tokens);
            }
            tokenRepository.save(token);
            LoginResponseDTO data = LoginResponseDTO.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            return new ApiResponse<>(data, "Login success");
        }
        throw new CustomRuntimeException(ErrorSystem.INTERNAL_SERVER_ERROR);
    }
}
