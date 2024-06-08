package com.electronics_store.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.token.UpdateTokenByAdminDTO;
import com.electronics_store.service.TokenService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/admin/account")
public class TokenController {

    TokenService tokenService;

    @GetMapping("/{id}/token")
    public ResponseEntity<ApiResponse<?>> getToken(@PathVariable Long id) {
        return ResponseEntity.ok().body(tokenService.getTokenByAdmin(id));
    }

    @PutMapping("{id}/token/{tokenId}")
    public ResponseEntity<ApiResponse<?>> updateToken(
            @PathVariable("id") Long userId,
            @PathVariable("tokenId") Long tokenId,
            @RequestBody UpdateTokenByAdminDTO updateTokenByAdminDTO) {
        return ResponseEntity.ok().body(tokenService.updateTokenByAdmin(userId, tokenId, updateTokenByAdminDTO));
    }
}
