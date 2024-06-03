package com.electronics_store.model.dto;

import com.electronics_store.exception.ErrorSystem;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    @JsonProperty(defaultValue = "100")
    private int code;

    private String message;

    @JsonProperty(defaultValue = "true")
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
}
