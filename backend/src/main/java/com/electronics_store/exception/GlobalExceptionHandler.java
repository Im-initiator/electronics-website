package com.electronics_store.exception;

import com.electronics_store.model.dto.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //handle Spring validation exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleValidationException(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        FieldError firstError = bindingResult.getFieldErrors().getFirst();
        String message = firstError.getDefaultMessage();
        return ApiResponse.builder()
                .code(300)
                .message(message).build();
    }

    //Xử lý lỗi trường hợp không tìm thấy tài khoản người dùng
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomUsernameNotFoundException.class)
    public ApiResponse<?> userNameNotFount(CustomUsernameNotFoundException ex){
        ErrorSystem errorSystem = ErrorSystem.USERNAME_NOTFOUND;
        return ApiResponse.builder()
                .code(errorSystem.getCode())
                .message(errorSystem.getMessage())
                .build();
    }

    //Xử lý lỗi không tìm thấy dữ liệu
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ApiResponse<?> dataNotFount(NullPointerException ex){
        return ApiResponse.builder()
                .code(104)
                .message(ex.getMessage())
                .build();
    }

    //xử lý lỗi khi trùng unique trên một trường dữ liệu
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ApiResponse<?> duplicateSQL(SQLIntegrityConstraintViolationException ex){
        String message = ex.getMessage();
        int index = message.indexOf('\'');
        message = message.substring(index+1,(message.indexOf('\'',index+1)));
        message += " already exist";
        return ApiResponse.builder()
                .code(300)
                .message(message)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomRuntimeException.class)
    public ApiResponse<?> handleCustomRuntimeException(CustomRuntimeException ex){
        ErrorSystem errorSystem = ex.getErrorSystem();
        return ApiResponse.builder()
                .code(errorSystem.getCode())
                .message(errorSystem.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MalformedJwtException.class)
    public ApiResponse<?> handleJWTException(MalformedJwtException ex){
        ErrorSystem errorSystem = ErrorSystem.ACCESS_TOKEN_IS_CORRECT;
        return ApiResponse.builder()
                .code(errorSystem.getCode())
                .message(errorSystem.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExpiredJwtException.class)
    public ApiResponse<?> jwtTokenExpired(ExpiredJwtException ex){
        ErrorSystem errorSystem = ErrorSystem.ACCESS_JWT_TOKEN_EXPIRED;
        return ApiResponse.builder()
                .code(errorSystem.getCode())
                .message(errorSystem.getMessage())
                .build();
    }



}
