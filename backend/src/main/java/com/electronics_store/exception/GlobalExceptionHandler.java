package com.electronics_store.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
    @ExceptionHandler(CustomUsernameNotFoundException.class)
    public ResponseEntity<?> userNameNotFount(CustomUsernameNotFoundException ex) {
        return ResponseEntity.status(ErrorSystem.USERNAME_NOTFOUND.getStatus())
                .body(new ApiResponse<>(ErrorSystem.USERNAME_NOTFOUND));
    }

    // Xử lý lỗi không tìm thấy dữ liệu
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> dataNotFount(NullPointerException ex) {
        String message = ex.getMessage();
        return ResponseEntity.status(ErrorSystem.INTERNAL_SERVER_ERROR.getStatus())
                .body(new ApiResponse<>(ErrorSystem.INTERNAL_SERVER_ERROR));
        //        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        //                .body(ApiResponse.builder()
        //                        .code(104)
        //                        .success(false)
        //                        .message(ex.getMessage())
        //                        .build());
    }

    // xử lý lỗi khi trùng unique trên một trường dữ liệu

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class, DataIntegrityViolationException.class})
    public ResponseEntity<?> duplicateSQL(SQLIntegrityConstraintViolationException ex) {
        String message = ex.getMessage();
        int index = message.indexOf('\'');
        message = message.substring(index + 1, (message.indexOf('\'', index + 1)));
        message += " already exist";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                        .code(300)
                        .success(false)
                        .message(message)
                        .build());
    }

    // xử lý lỗi được custom
    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<?> handleCustomRuntimeException(CustomRuntimeException ex) {
        if (ex.getErrorSystem() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.builder()
                            .code(400)
                            .success(false)
                            .message(ex.getMessage())
                            .build());
        }
        return ResponseEntity.status(ex.getErrorSystem().getStatus()).body(new ApiResponse<>(ex.getErrorSystem()));
    }

    // xử lý lỗi JWT

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<?> handleJWTException(MalformedJwtException ex) {
        return ResponseEntity.status(ErrorSystem.ACCESS_TOKEN_IS_CORRECT.getStatus())
                .body(new ApiResponse<>(ErrorSystem.ACCESS_TOKEN_IS_CORRECT));
    }

    // Xử lý lỗi JWT token hết hạn
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> jwtTokenExpired(ExpiredJwtException ex) {
        return ResponseEntity.status(ErrorSystem.ACCESS_JWT_TOKEN_EXPIRED.getStatus())
                .body(new ApiResponse<>(ErrorSystem.ACCESS_JWT_TOKEN_EXPIRED));
    }

    // Xử lý lỗi không tìm thấy resource, file mã nguon
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> resourceNotFound(NoResourceFoundException ex) {
        return ResponseEntity.status(ErrorSystem.RESOURCE_NOTFOUND.getStatus())
                .body(new ApiResponse<>(ErrorSystem.RESOURCE_NOTFOUND));
    }

    // Xử  lý  lỗi file quá lớn
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleMaxFilesize(ExpiredJwtException ex) {
        return ResponseEntity.status(ErrorSystem.MAX_FILES_SIZE.getStatus())
                .body(new ApiResponse<>(ErrorSystem.MAX_FILES_SIZE));
    }

    // xử lý lỗi truy cập vào method không được hỗ trợ
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handlerMethodNotAllow(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(ErrorSystem.METHOD_NOT_SUPPORT.getStatus())
                .body(new ApiResponse<>(ErrorSystem.METHOD_NOT_SUPPORT));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handlerMissingRequestParam(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(ErrorSystem.PAGE_NOT_FOUND.getStatus())
                .body(new ApiResponse<>(ErrorSystem.PAGE_NOT_FOUND));
    }

    // xử lý lỗi các truong hợp  còn lại
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<?> handleRunTimeException(Exception ex) {
        return ResponseEntity.status(ErrorSystem.INTERNAL_SERVER_ERROR.getStatus())
                .body(new ApiResponse<>(ErrorSystem.INTERNAL_SERVER_ERROR));
    }
}
