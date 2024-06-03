package com.electronics_store.enums;

public enum UserStatus {
    ACTIVE(0),
    DELETE(1),
    LOCK(2);
    private final Integer value;

    UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserStatus convert(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("User status value can not be null");
        }
        for (UserStatus status : values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid UserStatus value: " + value);
    }
}
