package com.electronics_store.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class CustomFileNotEmptyValidator implements ConstraintValidator<FileNotEmpty, MultipartFile> {

    @Override
    public void initialize(FileNotEmpty constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        return file != null && !file.isEmpty();
    }
}
