package com.electronics_store.service;

import com.electronics_store.model.dto.request.AccountDTO;
import com.electronics_store.model.entity.AccountEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface AccountService {
    Optional<AccountDTO> findByUserName(String userName);
    ResponseEntity<?> createAccountByUser(AccountDTO accountDTO);
    ResponseEntity<?> login(AccountDTO accountDTO, HttpServletRequest request);
}
