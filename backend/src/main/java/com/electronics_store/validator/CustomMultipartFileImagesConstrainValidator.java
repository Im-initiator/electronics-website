package com.electronics_store.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class CustomMultipartFileImagesConstrainValidator implements ConstraintValidator<MultipartFileImages,MultipartFile[]> {

    private static final String[] SUPPORTED_CONTENT_TYPES = {
            "image/jpg",
            "image/png",
            "image/jpeg"
    };

    @Override
    public void initialize(MultipartFileImages constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile[] multipartFiles, ConstraintValidatorContext constraintValidatorContext) {
        for (MultipartFile file : multipartFiles){
            String contentType = file.getContentType();
            assert contentType != null;
            if (!isSupportFile(contentType)){
                return false;
            }
        }
        return true;
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
