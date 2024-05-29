package com.electronics_store.utils;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.model.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseErrorUtils {

    public static void responseError(HttpServletResponse response, ErrorSystem errorSystem) throws IOException {
        response.setStatus(errorSystem.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .success(false)
                .code(errorSystem.getCode())
                .message(errorSystem.getMessage())
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
