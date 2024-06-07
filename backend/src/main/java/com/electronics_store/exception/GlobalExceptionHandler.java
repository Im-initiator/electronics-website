package com.electronics_store.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.electronics_store.model.dto.ApiResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // handle Spring validation exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError firstError = bindingResult.getFieldErrors().getFirst();
        String message = firstError.getDefaultMessage();
        return ApiResponse.builder().code(300).success(false).message(message).build();
    }

    // Xử lý lỗi trường hợp không tìm thấy tài khoản người dùng
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomUsernameNotFoundException.class)
    public ApiResponse<?> userNameNotFount(CustomUsernameNotFoundException ex) {
        return new ApiResponse<>(ErrorSystem.USERNAME_NOTFOUND);
    }

    // Xử lý lỗi không tìm thấy dữ liệu
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ApiResponse<?> dataNotFount(NullPointerException ex) {
        String message = ex.getMessage();
        if (message == null) {
            return new ApiResponse<>(ErrorSystem.INTERNAL_SERVER_ERROR);
        }
        return ApiResponse.builder()
                .code(104)
                .success(false)
                .message(ex.getMessage())
                .build();
    }

    // xử lý lỗi khi trùng unique trên một trường dữ liệu
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class, DataIntegrityViolationException.class})
    public ApiResponse<?> duplicateSQL(SQLIntegrityConstraintViolationException ex) {
        String message = ex.getMessage();
        int index = message.indexOf('\'');
        message = message.substring(index + 1, (message.indexOf('\'', index + 1)));
        message += " already exist";
        return ApiResponse.builder().code(300).success(false).message(message).build();
    }

    // xử lý lỗi được custom
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomRuntimeException.class)
    public ApiResponse<?> handleCustomRuntimeException(CustomRuntimeException ex) {
        return new ApiResponse<>(ex.getErrorSystem());
    }

    // xử lý lỗi JWT
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MalformedJwtException.class)
    public ApiResponse<?> handleJWTException(MalformedJwtException ex) {
        return new ApiResponse<>(ErrorSystem.ACCESS_TOKEN_IS_CORRECT);
    }

    // Xử lý lỗi JWT token hết hạn
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExpiredJwtException.class)
    public ApiResponse<?> jwtTokenExpired(ExpiredJwtException ex) {
        return new ApiResponse<>(ErrorSystem.ACCESS_JWT_TOKEN_EXPIRED);
    }

    // Xử lý lỗi không tìm thấy resource, file mã nguon
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public ApiResponse<?> resourceNotFound(NoResourceFoundException ex) {
        return new ApiResponse<>(ErrorSystem.RESOURCE_NOTFOUND);
    }

    // Xử  lý  lỗi file quá lớn
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ApiResponse<?> handleMaxFilesize(ExpiredJwtException ex) {
        return new ApiResponse<>(ErrorSystem.MAX_FILES_SIZE);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResponse<?> handlerMethodNotAllow(HttpRequestMethodNotSupportedException ex) {
        return new ApiResponse<>(ErrorSystem.METHOD_NOT_SUPPORT);
    }

    // xử lý lỗi các truong hợp  còn lại
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ApiResponse<?> handleRunTimeException(Exception ex) {
        return new ApiResponse<>(ErrorSystem.INTERNAL_SERVER_ERROR);
    }
}
