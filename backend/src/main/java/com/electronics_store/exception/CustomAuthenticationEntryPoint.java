package com.electronics_store.exception;

import com.electronics_store.utils.ResponseErrorUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorSystem err = switch (authException) {
            case UsernameNotFoundException usernameNotFoundException -> ErrorSystem.USERNAME_NOTFOUND;
            case AccountStatusException accountStatusException -> ErrorSystem.ACCOUNT_DISABLE;
            case BadCredentialsException badCredentialsException -> ErrorSystem.INCORRECT_ACCOUNT;
            case null, default -> ErrorSystem.UNAUTHORIZED;
        };
        ResponseErrorUtils.responseError(response,err);
    }
}
