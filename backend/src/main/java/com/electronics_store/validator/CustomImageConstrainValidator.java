package com.electronics_store.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class CustomImageConstrainValidator implements ConstraintValidator<ImageConstraint, MultipartFile>{

    private static final String[] SUPPORTED_CONTENT_TYPES = {
        "image/jpg",
        "image/png",
        "image/jpeg"
    };

    @Override
    public void initialize(ImageConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        String contentType = file.getContentType();
        assert contentType != null;
        return isSupportFile(file.getContentType());
    }

    private boolean isSupportFile(String contentType){
        for (String pathFile : SUPPORTED_CONTENT_TYPES){
            if (pathFile.equals(contentType)){
                return true;
            }
        }
        return false;
    }
}
