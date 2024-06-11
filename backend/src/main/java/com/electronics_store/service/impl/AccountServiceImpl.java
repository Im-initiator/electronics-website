package com.electronics_store.service.impl;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import com.electronics_store.auth.userDetails.CustomUserDetails;
import com.electronics_store.auth.userDetails.CustomUserDetailsService;
import com.electronics_store.enums.RoleType;
import com.electronics_store.enums.State;
import com.electronics_store.enums.UserStatus;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.mapper.AccountMapper;
import com.electronics_store.mapper.DataMapper;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.BaseDTO;
import com.electronics_store.model.dto.request.account.*;
import com.electronics_store.model.dto.request.auth.LoginDTO;
import com.electronics_store.model.dto.response.LoginResponseDTO;
import com.electronics_store.model.dto.response.account.GetAccountByAdminDTO;
import com.electronics_store.model.dto.response.account.UpdateAccountByUserResponseDTO;
import com.electronics_store.model.entity.AccountEntity;
import com.electronics_store.model.entity.RoleEntity;
import com.electronics_store.model.entity.TokenEntity;
import com.electronics_store.repository.AccountRepository;
import com.electronics_store.repository.RoleRepository;
import com.electronics_store.repository.TokenRepository;
import com.electronics_store.service.AccountService;
import com.electronics_store.service.jwt.JwtService;
import com.electronics_store.utils.FileUtils;
import com.electronics_store.utils.ResponseUtils;
import com.electronics_store.utils.SecurityUtils;

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
    AccountMapper accountMapper;
    CacheManager cacheManager;

    static String URL_USER_DEFAULT = "\\files_upload\\images\\userDefault.png";

    @Override
    public Optional<CreateAccountByUserRequestDTO> findByUserName(String userName) {
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ApiResponse<LoginResponseDTO> createAccountByUser(
            CreateAccountByUserRequestDTO createAccountByUserRequestDTO) {
        if (accountRepository.isExitingUserName(createAccountByUserRequestDTO.getUserName())) {
            throw new CustomRuntimeException(ErrorSystem.USER_NAME_IS_EXISTS);
        }
        RoleEntity role = roleRepository
                .findByName(RoleType.USER.name())
                .orElseThrow(() -> new NullPointerException("Role User not found"));
        AccountEntity accountEntity = DataMapper.toEntity(createAccountByUserRequestDTO, AccountEntity.class);
        accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
        accountEntity.setRoles(Set.of(role));
        accountEntity.setThumbnail(URL_USER_DEFAULT);
        accountEntity.setStatus(UserStatus.ACTIVE);
        accountRepository.save(accountEntity);
        AccountEntity account = accountRepository
                .findByUserName(createAccountByUserRequestDTO.getUserName())
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

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public ApiResponse<?> createAccountByAdmin(CreateAccountByAdminRequestDTO account) {
        if (accountRepository.isExitingUserName(account.getUserName())) {
            throw new CustomRuntimeException(ErrorSystem.USER_NAME_IS_EXISTS);
        }
        AccountEntity accountEntity = DataMapper.toEntity(account, AccountEntity.class);
        accountEntity.setPassword(passwordEncoder.encode(accountEntity.getPassword()));
        Set<RoleEntity> roles = roleRepository.findByIdIn(account.getRoleIds());
        accountEntity.setRoles(roles);
        accountEntity.setStatus(UserStatus.ACTIVE);
        accountEntity.setThumbnail(URL_USER_DEFAULT);
        accountRepository.save(accountEntity);
        return new ApiResponse<>("create user success");
    }

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

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResponse<UpdateAccountByUserResponseDTO> updateUserInformationByUser(
            UpdateAccountByUserRequestDTO updateAccountByUserRequestDTO) {
        updateAccountByUserRequestDTO.setId(SecurityUtils.getPrincipalId());
        String filename = updateAccountByUserRequestDTO.getImage().getOriginalFilename();
        String image = null;
        AccountEntity account = accountRepository
                .findById(updateAccountByUserRequestDTO.getId())
                .orElseThrow(() -> new CustomRuntimeException(ErrorSystem.USER_NOT_FOUND));
        try {
            if ((account != null)) {
                DataMapper.map(updateAccountByUserRequestDTO, account);
                if (!FileUtils.isImageExisted(filename)) {
                    image = FileUtils.saveImage(updateAccountByUserRequestDTO.getImage());
                    String oldImage = account.getThumbnail();
                    if (oldImage != null && !oldImage.equals(URL_USER_DEFAULT)) {
                        if (!FileUtils.deleteImage(oldImage)) {
                            FileUtils.deleteImage(image);
                            throw new CustomRuntimeException(ErrorSystem.INTERNAL_SERVER_ERROR);
                        }
                    }
                    account.setThumbnail(image);
                }
                account = accountRepository.save(account);
                UpdateAccountByUserResponseDTO accountResponse =
                        DataMapper.toDTO(account, UpdateAccountByUserResponseDTO.class);
                return new ApiResponse<>(accountResponse, "Update account successful");
            }
            return new ApiResponse<>(ErrorSystem.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            if (image != null) {
                if (FileUtils.checkPath(image)) {
                    FileUtils.deleteImage(image);
                }
            }
            throw new CustomRuntimeException(ErrorSystem.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public ApiResponse<?> findAllAccountActiveByAdmin(Map<String, String> request) {
        try {
            int page = Integer.parseInt(request.get("page")) - 1;
            int limit = Integer.parseInt(request.get("limit"));
            State state = State.convert(Integer.parseInt(request.get("state")));
            UserStatus status = UserStatus.convert(Integer.parseInt( request.get("user_status")));
            Pageable pageable = PageRequest.of(page, limit);
            Page<AccountEntity> pageAccount = null;
            String name;
            try {
                name = (String) request.get("name");
            } catch (NullPointerException e) {
                name = null;
            }
            if (name == null) {
                pageAccount = accountRepository.findAllAccountActiveOrderByCreatedDate(state, status, pageable);
            } else {
                pageAccount = accountRepository.findAllAccountActiveAndNameContainOrderByCreatedDate(
                        state, status, name, pageable);
            }

            Map<String, Object> result = ResponseUtils.getPageResponse(
                    pageable, pageAccount, accountMapper, a -> accountMapper.toAccountDTO((AccountEntity) a));
            return new ApiResponse<>(result, "Get all account success");
        } catch (ClassCastException | NumberFormatException | NullPointerException e) {
            throw new CustomRuntimeException(ErrorSystem.PAGE_NOT_FOUND);
        }
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Transactional(rollbackOn = Exception.class)
    public ApiResponse<?> updateAccountByAdmin(Long id, UpdateAccountByAdmin account) {
        AccountEntity accountEntity = accountRepository
                .findById(id)
                .orElseThrow(() -> new CustomRuntimeException(ErrorSystem.USER_NOT_FOUND));
        if (account.getRoleIds().isEmpty()) {
            throw new CustomRuntimeException(ErrorSystem.ROLE_NULL);
        }
        Set<RoleEntity> ids = roleRepository.findByIdIn(account.getRoleIds());
        accountEntity.setRoles(ids);
        accountEntity.setStatus(UserStatus.convert(account.getStatus()));
        accountRepository.save(accountEntity);
        return new ApiResponse<>("Update account success");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public ApiResponse<?> updateAccountByAdmin(BaseDTO baseAccount) {
        AccountEntity accountEntity = accountRepository
                .findById(baseAccount.getId())
                .orElseThrow(() -> new CustomRuntimeException(ErrorSystem.USER_NOT_FOUND));
        accountEntity.setState(State.convert(baseAccount.getState()));
        accountRepository.save(accountEntity);
        if (baseAccount.getState() == 0) {
            return new ApiResponse<>("Remove account success");
        }
        return new ApiResponse<>("Restore account success");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public ApiResponse<?> updateAccountByAdmin(Long id, State state) {
        AccountEntity accountEntity = accountRepository
                .findById(id)
                .orElseThrow(() -> new CustomRuntimeException(ErrorSystem.USER_NOT_FOUND));
        accountEntity.setState(state);
        accountRepository.save(accountEntity);
        if (state == State.DELETE) {
            return new ApiResponse<>("Remove account success");
        }
        return new ApiResponse<>("Restore account success");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Override
    public ApiResponse<GetAccountByAdminDTO> getAccountByAdmin(Long id) {
        AccountEntity accountEntity = accountRepository
                .findAccountById(id)
                .orElseThrow(() -> new CustomRuntimeException(ErrorSystem.USER_NOT_FOUND));
        return new ApiResponse<>(accountMapper.toGetAccountByAdminDTO(accountEntity), "Get account success");
    }
}
