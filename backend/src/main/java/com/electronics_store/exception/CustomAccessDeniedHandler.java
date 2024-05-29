package com.electronics_store.exception;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.electronics_store.utils.ResponseErrorUtils;

//được gọi khi spring security phát hiện ngời dùng truy cập vào tài nguyên không được cho phép
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(
            HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        ErrorSystem errorSystem = ErrorSystem.ACCESS_DENIED;
        ResponseErrorUtils.responseError(response, errorSystem);
    }
}
