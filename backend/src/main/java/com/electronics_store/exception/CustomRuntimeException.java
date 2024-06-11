package com.electronics_store.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomRuntimeException extends RuntimeException {
    private ErrorSystem errorSystem;

    public CustomRuntimeException(String message) {
        super(message);
        this.errorSystem = null;
    }
}
