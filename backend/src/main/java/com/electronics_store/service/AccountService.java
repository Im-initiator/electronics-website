package com.electronics_store.service;

import java.util.Map;
import java.util.Optional;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.BaseDTO;
import com.electronics_store.model.dto.request.account.CreateAccountByAdminRequestDTO;
import com.electronics_store.model.dto.request.account.CreateAccountByUserRequestDTO;
import com.electronics_store.model.dto.request.account.UpdateAccountByAdmin;
import com.electronics_store.model.dto.request.account.UpdateAccountByUserRequestDTO;
import com.electronics_store.model.dto.response.LoginResponseDTO;
import com.electronics_store.model.dto.response.account.GetAccountByAdminByAdminDTO;
import com.electronics_store.model.dto.response.account.UpdateAccountByUserDTO;

public interface AccountService {
    Optional<CreateAccountByUserRequestDTO> findByUserName(String userName);

    ApiResponse<LoginResponseDTO> createAccountByUser(CreateAccountByUserRequestDTO createAccountByUserRequestDTO);

    ApiResponse<?> createAccountByAdmin(CreateAccountByAdminRequestDTO account);

    ApiResponse<UpdateAccountByUserDTO> updateUserInformationByUser(
            UpdateAccountByUserRequestDTO updateAccountByUserRequestDTO);

    ApiResponse<?> findAllAccountActiveByAdmin(Map<String, String> request);

    ApiResponse<?> updateAccountByAdmin(Long id, UpdateAccountByAdmin account);

    ApiResponse<?> updateAccountByAdmin(BaseDTO baseAccount);

    ApiResponse<?> updateAccountByAdmin(Long id, State state);

    ApiResponse<GetAccountByAdminByAdminDTO> getAccountByAdmin(Long id);
}
