package com.electronics_store.service;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.BaseDTO;
import com.electronics_store.model.dto.request.account.*;
import com.electronics_store.model.dto.response.LoginResponseDTO;
import com.electronics_store.model.dto.response.account.ListAccountByAdminDTO;
import com.electronics_store.model.dto.response.account.UpdateAccountByUserResponseDTO;

public interface AccountService {
    Optional<CreateAccountByUserRequestDTO> findByUserName(String userName);

    ApiResponse<LoginResponseDTO> createAccountByUser(CreateAccountByUserRequestDTO createAccountByUserRequestDTO);

    ApiResponse<?> createAccountByAdmin(CreateAccountByAdminRequestDTO account);

    ApiResponse<LoginResponseDTO> login(
            CreateAccountByUserRequestDTO createAccountByUserRequestDTO, HttpServletRequest request);

    ApiResponse<UpdateAccountByUserResponseDTO> updateUserInformationByUser(
            UpdateAccountByUserRequestDTO updateAccountByUserRequestDTO);

    ApiResponse<ListAccountByAdminDTO> findAllAccountActiveByAdmin(PageAccountByAdminDTO page);

    ApiResponse<?> updateAccountByAdmin(Long id, UpdateAccountByAdmin account);

    ApiResponse<?> updateAccountByAdmin(BaseDTO baseAccount);

    ApiResponse<?> updateAccountByAdmin(Long id, State state);
}
