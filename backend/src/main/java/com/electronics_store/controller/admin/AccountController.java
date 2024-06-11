package com.electronics_store.controller.admin;

import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.account.*;
import com.electronics_store.model.dto.response.account.GetAccountByAdminDTO;
import com.electronics_store.service.AccountService;
import com.electronics_store.service.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "_admin/account", description = "Account Management")
@RestController(value = "userAdminController")
@RequestMapping("/admin/account")
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final TokenService tokenService;

    @Operation(summary = "Get page", description = "params: page,limit,state,user_status,name(allow null)")
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPageAccount(@RequestParam Map<String, String> request) {
        return ResponseEntity.ok().body(accountService.findAllAccountActiveByAdmin(request));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> addUser(@Valid @RequestBody CreateAccountByAdminRequestDTO user) {
        return ResponseEntity.ok().body(accountService.createAccountByAdmin(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetAccountByAdminDTO>> getUser(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountByAdmin(id));
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
