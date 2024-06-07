package com.electronics_store.controller.user;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.account.UpdateAccountByUserRequestDTO;
import com.electronics_store.model.dto.response.account.UpdateAccountByUserResponseDTO;
import com.electronics_store.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController(value = "userAccountController")
@RequestMapping("/user/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ApiResponse<UpdateAccountByUserResponseDTO>> updateAccountByUser(
            @Valid @ModelAttribute UpdateAccountByUserRequestDTO account) {
        return ResponseEntity.ok(accountService.updateUserInformationByUser(account));
    }
}
