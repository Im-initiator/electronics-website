package com.electronics_store.model.dto;

import com.electronics_store.exception.ErrorSystem;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int code;
    private String message;
    private boolean success;

    private T data;

    public ApiResponse(ErrorSystem errorSystem) {
        this.success = false;
        this.message = errorSystem.getMessage();
        this.code = errorSystem.getCode();
        this.data = null;
    }

    public ApiResponse(T object, String message) {
        this.data = object;
        this.success = true;
        this.code = 100;
        this.message = message;
    }

    public ApiResponse(String message) {
        this.data = null;
        this.success = true;
        this.code = 100;
        this.message = message;
    }
}
