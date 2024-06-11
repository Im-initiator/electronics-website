package com.electronics_store.auth.userDetails;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.electronics_store.enums.UserStatus;
import com.electronics_store.model.entity.AccountEntity;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails {
    AccountEntity accountEntity;

    public CustomUserDetails(AccountEntity account) {
        this.accountEntity = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return accountEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return accountEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return accountEntity.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountEntity.getStatus() != UserStatus.LOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return accountEntity.getStatus() == UserStatus.ACTIVE;
    }

    public Long getId() {
        return this.accountEntity.getId();
    }

    public String getEmail() {
        return this.accountEntity.getEmail();
    }

}
