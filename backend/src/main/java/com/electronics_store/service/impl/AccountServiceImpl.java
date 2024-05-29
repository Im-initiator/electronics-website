package com.electronics_store.service.impl;

import java.util.Optional;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import com.electronics_store.auth.userDetails.CustomUserDetails;
import com.electronics_store.enums.RoleType;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.mapper.DataMapper;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.account.CreateAccountByUserDTO;
import com.electronics_store.model.dto.response.LoginResponseDTO;
import com.electronics_store.model.entity.AccountEntity;
import com.electronics_store.model.entity.RoleEntity;
import com.electronics_store.model.entity.TokenEntity;
import com.electronics_store.repository.AccountRepository;
import com.electronics_store.repository.RoleRepository;
import com.electronics_store.repository.TokenRepository;
import com.electronics_store.service.AccountService;
import com.electronics_store.service.jwt.JwtService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Optional<CreateAccountByUserDTO> findByUserName(String userName) {
        return null;
    }

    @Override
    public ApiResponse<?> createAccountByUser(CreateAccountByUserDTO createAccountByUserDTO) {
        RoleEntity role = roleRepository
                .findByCode(RoleType.USER.name())
                .orElseThrow(() -> new NullPointerException("Role User not found"));
        AccountEntity accountEntity = DataMapper.toEntity(createAccountByUserDTO, AccountEntity.class);
        accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
        accountEntity.setRoles(Set.of(role));
        accountRepository.save(accountEntity);
        ApiResponse<CreateAccountByUserDTO> response = ApiResponse.<CreateAccountByUserDTO>builder()
                .data(createAccountByUserDTO)
                .build();
        return response;
    }

    @Override
    @Transactional
    public ApiResponse<?> login(CreateAccountByUserDTO createAccountByUserDTO, HttpServletRequest request) {
        WebAuthenticationDetails webAuthenticationDetailsSource =
                new WebAuthenticationDetailsSource().buildDetails(request);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        createAccountByUserDTO.getUserName(), createAccountByUserDTO.getPassword());
        usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken)
                authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        usernamePasswordAuthenticationToken.setDetails(webAuthenticationDetailsSource);
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
