package com.electronics_store.auth.userDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.electronics_store.exception.CustomUsernameNotFoundException;
import com.electronics_store.model.entity.AccountEntity;
import com.electronics_store.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountRepository
                .findByUserName(username)
                .orElseThrow(() -> new CustomUsernameNotFoundException("User name not fount"));
        return new CustomUserDetails(accountEntity);
    }
}
