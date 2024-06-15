package com.electronics_store.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})//loại  phạm vi áp dụng cho annotation
@Retention(RetentionPolicy.RUNTIME) // thời điểm sử dụng annotation
@Documented // cho phép chú thích được hiển thị trong tài liệu
@Constraint(validatedBy = {CustomMultipartFileImagesConstrainValidator.class}) //
public @interface MultipartFileImages {
    String message() default "Only png,jpeg,jpg files are allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
