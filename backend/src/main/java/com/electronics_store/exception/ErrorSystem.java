package com.electronics_store.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


@Getter
public enum ErrorSystem {

    USERNAME_NOTFOUND(104,HttpStatus.NOT_FOUND,"User name not found"),
    ACCESS_DENIED(103,HttpStatus.FORBIDDEN,"You do not have access to this resource"),
    UNAUTHORIZED(101,HttpStatus.UNAUTHORIZED,"You need authentication to access"),
    ACCOUNT_DISABLE(201,HttpStatus.FORBIDDEN,"Your account has been locked"),
    INCORRECT_ACCOUNT(200,HttpStatus.BAD_REQUEST,"User name or password is incorrect"),
    ACCESS_TOKEN_IS_CORRECT(300,HttpStatus.BAD_REQUEST,"access token is incorrect"),
    REFRESH_TOKEN_IS_CORRECT(300,HttpStatus.BAD_REQUEST,"refresh token is incorrect"),
    DONT_SAVE_TOKEN(400,HttpStatus.INTERNAL_SERVER_ERROR,"have error from server"),
    DATA_IS_WRONG_FORMAT(400,HttpStatus.BAD_REQUEST,"Data is in wrong format"),
    ACCESS_JWT_TOKEN_EXPIRED(111,HttpStatus.BAD_REQUEST,"access token is expired"),
    REFRESH_JWT_TOKEN_EXPIRED(112,HttpStatus.BAD_REQUEST,"refresh token is expired")
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode status;
    ErrorSystem(int code, HttpStatusCode status, String message){
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
