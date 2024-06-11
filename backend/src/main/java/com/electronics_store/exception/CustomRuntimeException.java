package com.electronics_store.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {
    private final ErrorSystem errorSystem;
    private HttpStatus status;

    public CustomRuntimeException(String message) {
        super(message);
        this.errorSystem = null;
    }

    public CustomRuntimeException(ErrorSystem errorSystem) {
        super(errorSystem.getMessage());
        this.errorSystem = errorSystem;
        this.status = null;
    }

    public CustomRuntimeException(String message, HttpStatus status) {
        super(message);
        this.errorSystem = null;
        this.status = status;
    }
}
