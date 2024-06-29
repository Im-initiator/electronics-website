package com.electronics_store.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.electronics_store.model.dto.ApiResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // handle Spring validation exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError firstError = bindingResult.getFieldErrors().getFirst();
        String message = firstError.getDefaultMessage();
        if (message!=null && message.length()>50){
                message = "Invalid input";
        }
        return ApiResponse.builder().code(400).success(false).message(message).build();
    }

    // Xử lý lỗi trường hợp không tìm thấy tài khoản người dùng
    @ExceptionHandler(CustomUsernameNotFoundException.class)
    public ResponseEntity<?> userNameNotFount(CustomUsernameNotFoundException ex) {
        ErrorSystem error = ErrorSystem.USERNAME_NOTFOUND;
        return ResponseEntity.status(error.getStatus())
                .body(new ApiResponse<>(error));
    }

    // Xử lý lỗi không tìm thấy dữ liệu
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> dataNotFount(NullPointerException ex) {
        String message = ex.getMessage();
        ErrorSystem error = ErrorSystem.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(error.getStatus())
                .body(new ApiResponse<>(error));
        //        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        //                .body(ApiResponse.builder()
        //                        .code(104)
        //                        .success(false)
        //                        .message(ex.getMessage())
        //                        .build());
    }

    //    // xử lý lỗi khi trùng unique trên một trường dữ liệu
    //
    //    @ExceptionHandler({SQLIntegrityConstraintViolationException.class, DataIntegrityViolationException.class})
    //    public ResponseEntity<?> duplicateSQL(SQLIntegrityConstraintViolationException ex) {
    //        String message = ex.getMessage();
    //        int index = message.indexOf('\'');
    //        message = message.substring(index + 1, (message.indexOf('\'', index + 1)));
    //        message += " already exist";
    //        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //                .body(ApiResponse.builder()
    //                        .code(300)
    //                        .success(false)
    //                        .message(message)
    //                        .build());
    //    }

    // xử lý lỗi được custom
    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<?> handleCustomRuntimeException(CustomRuntimeException ex) {
        if (ex.getErrorSystem() == null) {
            if (ex.getStatus() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ApiResponse.builder()
                                .code(400)
                                .success(false)
                                .message(ex.getMessage())
                                .build());
            } else {
                return ResponseEntity.status(ex.getStatus())
                        .body(ApiResponse.builder()
                                .code(ex.getStatus().value())
                                .success(false)
                                .message(ex.getMessage())
                                .build());
            }
        }
        return ResponseEntity.status(ex.getErrorSystem().getStatus()).body(new ApiResponse<>(ex.getErrorSystem()));
    }

    // xử lý lỗi JWT

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<?> handleJWTException(MalformedJwtException ex) {
        ErrorSystem error = ErrorSystem.ACCESS_TOKEN_IS_CORRECT;
        return ResponseEntity.status(error.getStatus())
                .body(new ApiResponse<>(error));
    }

    // Xử lý lỗi JWT token hết hạn
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> jwtTokenExpired(ExpiredJwtException ex) {
        ErrorSystem error = ErrorSystem.ACCESS_JWT_TOKEN_EXPIRED;
        return ResponseEntity.status(error.getStatus())
                .body(new ApiResponse<>(error));
    }

    // Xử lý lỗi không tìm thấy resource, file mã nguon
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> resourceNotFound(NoResourceFoundException ex) {
        ErrorSystem error = ErrorSystem.RESOURCE_NOTFOUND;
        return ResponseEntity.status(error.getStatus())
                .body(new ApiResponse<>(error));
    }

    // Xử  lý  lỗi file quá lớn
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleMaxFilesize(ExpiredJwtException ex) {
        ErrorSystem error = ErrorSystem.MAX_FILES_SIZE;
        return ResponseEntity.status(error.getStatus())
                .body(new ApiResponse<>(error));
    }

    // xử lý lỗi truy cập vào method không được hỗ trợ
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handlerMethodNotAllow(HttpRequestMethodNotSupportedException ex) {
        ErrorSystem error = ErrorSystem.METHOD_NOT_SUPPORT;
        return ResponseEntity.status(error.getStatus())
                .body(new ApiResponse<>(error));
    }

    // xử lý khi thiếu tham số truy vấn
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handlerMissingRequestParam(MissingServletRequestParameterException ex) {
        ErrorSystem error = ErrorSystem.PAGE_NOT_FOUND;
        return ResponseEntity.status(error.getStatus())
                .body(new ApiResponse<>(error));
    }
    //xử lý khi kiểu request param  không đúng kiểu dữ liệu
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handlerMissingRequestParam(MethodArgumentTypeMismatchException ex) {
        ErrorSystem errorSystem = ErrorSystem.ARGUMENT_IS_CORRECT;
        return ResponseEntity.status(errorSystem.getStatus())
                .body(new ApiResponse<>(errorSystem));
    }



    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleUserNamePasswordIsCorrect(AuthenticationException ex) {
        ErrorSystem error = ErrorSystem.INCORRECT_ACCOUNT;
        return ResponseEntity.status(error.getStatus())
                .body(new ApiResponse<>(error));
    }

    // xử lý lỗi khi custom validate trường dữ liệu
    // (Khi class được đánh dấu là @Validated nêu filed nào lỗi thì nó ném ra ngoại lệ này
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleCustomValidatorException(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApiResponse.builder().code(400).success(false).message(ex.getConstraintViolations().iterator().next().getMessage()).build()
        );
    }

    // xử lý lỗi khi custom validate trường dữ liệu (Nếu chỉ valid trên một method của class không được đaánh dấu
    //@Validated thì nó ném ra ngoại lệ này)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<?> handleMethodValidationException(HandlerMethodValidationException ex) {
        MethodArgumentNotValidException validationException = (MethodArgumentNotValidException) ex.getCause();
        BindingResult bindingResult = validationException.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // xử lý lỗi các truong hợp  còn lại
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<?> handleRunTimeException(Exception ex) {
        ErrorSystem error = ErrorSystem.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(error.getStatus())
                .body(new ApiResponse<>(error));
    }
}
