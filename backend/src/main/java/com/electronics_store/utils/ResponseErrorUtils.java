package com.electronics_store.utils;

import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.model.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;

public class ResponseErrorUtils {


    public static void responseError(HttpServletResponse response, ErrorSystem errorSystem) throws IOException {
        response.setStatus(errorSystem.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(errorSystem.getCode())
                .message(errorSystem.getMessage())
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
