package com.electronics_store.exception;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.electronics_store.utils.ResponseErrorUtils;

// được gọi khi spring security nhận biết người dùng truy cập  vào tài nguyên yêu cầu xác  thực nhưng chưa xác thực
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        logger.error(authException.getMessage());
        ErrorSystem err =
                switch (authException) {
                    case UsernameNotFoundException usernameNotFoundException -> ErrorSystem.USERNAME_NOTFOUND;
                    case AccountStatusException accountStatusException -> ErrorSystem.ACCOUNT_DISABLE;
                    case BadCredentialsException badCredentialsException -> ErrorSystem.INCORRECT_ACCOUNT;
                        // spotless:off
                    default -> ErrorSystem.UNAUTHORIZED;
                };
        ResponseErrorUtils.responseError(response, err);
    }
}
