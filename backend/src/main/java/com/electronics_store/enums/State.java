package com.electronics_store.enums;

import com.electronics_store.exception.CustomRuntimeException;

public enum State {
    ACTIVE(1),
    DELETE(0);

    private final Integer value;

    State(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static State convert(Integer value) {
        if (value == null) {
            throw new CustomRuntimeException("Status value can not be null");
        }
        if (value == 1) {
            return ACTIVE;
        } else if (value == 0) {
            return DELETE;
        } else {
            throw new CustomRuntimeException("Status value is invalid");
        }
    }
}
