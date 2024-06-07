package com.electronics_store.controller.admin;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.account.CreateAccountByAdminRequestDTO;
import com.electronics_store.model.dto.request.account.PageAccountByAdminDTO;
import com.electronics_store.model.dto.request.account.UpdateAccountByAdmin;
import com.electronics_store.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController(value = "userAdminController")
@RequestMapping("/admin/account")
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPageAccount(@Valid @RequestBody PageAccountByAdminDTO page) {
        return ResponseEntity.ok().body(accountService.findAllAccountActiveByAdmin(page));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> addUser(@Valid @RequestBody CreateAccountByAdminRequestDTO user) {
        return ResponseEntity.ok().body(accountService.createAccountByAdmin(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateUser(
            @PathVariable Long id, @Valid @RequestBody UpdateAccountByAdmin user) {
        return ResponseEntity.ok().body(accountService.updateAccountByAdmin(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(accountService.updateAccountByAdmin(id, State.DELETE));
    }

    @PutMapping("{id}/restore")
    public ResponseEntity<ApiResponse<?>> restoreUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(accountService.updateAccountByAdmin(id, State.ACTIVE));
    }


}
