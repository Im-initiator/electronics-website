package com.electronics_store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorSystem {
    USERNAME_NOTFOUND(104, HttpStatus.NOT_FOUND, "User name not found"),
    USER_NAME_IS_EXISTS(104, HttpStatus.BAD_REQUEST, "User name already exists"),
    EMAIL_IS_EXISTS(104, HttpStatus.BAD_REQUEST, "Email already exists"),
    USER_NOT_FOUND(104, HttpStatus.NOT_FOUND, "User not found"),
    ACCESS_DENIED(103, HttpStatus.FORBIDDEN, "You do not have access to this resource"),
    UNAUTHORIZED(401, HttpStatus.UNAUTHORIZED, "You need authentication to access"),
    ACCOUNT_DISABLE(201, HttpStatus.FORBIDDEN, "Your account has been locked"),
    INCORRECT_ACCOUNT(400, HttpStatus.BAD_REQUEST, "User name or password is incorrect"),
    ACCESS_TOKEN_IS_CORRECT(300, HttpStatus.BAD_REQUEST, "access token is incorrect"),
    REFRESH_TOKEN_IS_CORRECT(300, HttpStatus.BAD_REQUEST, "refresh token is incorrect"),
    DATA_IS_WRONG_FORMAT(400, HttpStatus.BAD_REQUEST, "Data is in wrong format"),
    ACCESS_JWT_TOKEN_EXPIRED(111, HttpStatus.BAD_REQUEST, "access token is expired"),
    REFRESH_JWT_TOKEN_EXPIRED(112, HttpStatus.BAD_REQUEST, "refresh token is expired"),
    MAX_FILES_SIZE(401, HttpStatus.BAD_REQUEST, "Max files size is 50MB"),
    RESOURCE_NOTFOUND(404, HttpStatus.NOT_FOUND, "resource not found"),
    INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "have error from server"),
    ROLE_NULL(400, HttpStatus.BAD_REQUEST, "Role must be not null"),
    METHOD_NOT_SUPPORT(405, HttpStatus.METHOD_NOT_ALLOWED, "This method is not supported"),
    PAGE_NOT_FOUND(404, HttpStatus.NOT_FOUND, "Page not found"),
    TOKEN_NOT_FOUND(404, HttpStatus.NOT_FOUND, "Token not found"),
    ARGUMENT_IS_CORRECT(400,HttpStatus.BAD_REQUEST,"Request param is correct"),
    SAVE_IMAGE_FAILED(500, HttpStatus.INTERNAL_SERVER_ERROR, "Save image failed");

    private final int code;
    private final String message;
    private final HttpStatusCode status;

    ErrorSystem(int code, HttpStatusCode status, String message) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
