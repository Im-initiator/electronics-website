package com.electronics_store.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.electronics_store.auth.userDetails.CustomUserDetails;
import com.electronics_store.model.entity.AccountEntity;

public class SecurityUtils {

    public static Long getPrincipalId() {
        CustomUserDetails customUserDetails = (CustomUserDetails)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getId();
    }

    public static AccountEntity getPrincipal() {
        CustomUserDetails customUserDetails = (CustomUserDetails)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetails.getAccountEntity();
    }
}
