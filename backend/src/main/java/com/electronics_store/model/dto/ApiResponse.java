package com.electronics_store.model.dto;

import com.electronics_store.exception.ErrorSystem;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    @JsonProperty(defaultValue = "100")
    private int code;
    private String message;
    @JsonProperty(defaultValue = "true")
    private boolean success;
    private T data;

    public ApiResponse(ErrorSystem errorSystem){
        this.success = false;
        this.message = errorSystem.getMessage();
        this.code = errorSystem.getCode();
        this.data = null;
    }

}
