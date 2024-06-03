package com.electronics_store.service.impl;

import java.util.Optional;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import com.electronics_store.auth.userDetails.CustomUserDetails;
import com.electronics_store.auth.userDetails.CustomUserDetailsService;
import com.electronics_store.enums.RoleType;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.mapper.DataMapper;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.account.CreateAccountByUserDTORequest;
import com.electronics_store.model.dto.response.LoginResponseDTO;
import com.electronics_store.model.entity.AccountEntity;
import com.electronics_store.model.entity.RoleEntity;
import com.electronics_store.model.entity.TokenEntity;
import com.electronics_store.repository.AccountRepository;
import com.electronics_store.repository.RoleRepository;
import com.electronics_store.repository.TokenRepository;
import com.electronics_store.service.AccountService;
import com.electronics_store.service.jwt.JwtService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AccountServiceImpl implements AccountService {
    AuthenticationManager authenticationManager;
    JwtService jwtService;
    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    TokenRepository tokenRepository;
    CustomUserDetailsService customUserDetailsService;

    @Override
    public Optional<CreateAccountByUserDTORequest> findByUserName(String userName) {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ApiResponse<?> createAccountByUser(CreateAccountByUserDTORequest createAccountByUserDTORequest) {
        RoleEntity role = roleRepository
                .findByName(RoleType.USER.name())
                .orElseThrow(() -> new NullPointerException("Role User not found"));
        AccountEntity accountEntity = DataMapper.toEntity(createAccountByUserDTORequest, AccountEntity.class);
        accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
        accountEntity.setRoles(Set.of(role));
        accountRepository.save(accountEntity);
        AccountEntity account = accountRepository
                .findByUserName(createAccountByUserDTORequest.getUserName())
                .orElse(null);
        CustomUserDetails userDetails = new CustomUserDetails(account);
        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        // save token
        TokenEntity token = TokenEntity.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .account(account)
                .build();
        tokenRepository.save(token);
        // generate response
        LoginResponseDTO response = LoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        return new ApiResponse<LoginResponseDTO>(response, "create account successful");
    }

    @Override
    @Transactional
    public ApiResponse<?> login(
            CreateAccountByUserDTORequest createAccountByUserDTORequest, HttpServletRequest request) {
        // information web
        WebAuthenticationDetails webAuthenticationDetailsSource =
                new WebAuthenticationDetailsSource().buildDetails(request);
        // create credentials
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        createAccountByUserDTORequest.getUserName(), createAccountByUserDTORequest.getPassword());
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
                tokenRepository.deleteOldTokenByUser(userDetails.getId());
            }
            tokenRepository.save(token);
            LoginResponseDTO data = LoginResponseDTO.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            return ApiResponse.builder().message("Login success").data(data).build();
        }
        throw new CustomRuntimeException(ErrorSystem.DONT_SAVE_TOKEN);
    }
}
