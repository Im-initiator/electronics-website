package com.electronics_store.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})//loại  phạm vi áp dụng cho annotation
@Retention(RetentionPolicy.RUNTIME) // thời điểm sử dụng annotation
@Documented // cho phép chú thích được hiển thị trong tài liệu
@Constraint(validatedBy = {CustomFileNotEmptyValidator.class})
public @interface FileNotEmpty {

    String message() default "File is not empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
