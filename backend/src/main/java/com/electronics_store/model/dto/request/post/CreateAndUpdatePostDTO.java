package com.electronics_store.model.dto.request.post;

import com.electronics_store.validator.ImageConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CreateAndUpdatePostDTO {
    @NotBlank
    private String category;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String content;

    @NotNull
    @ImageConstraint
    private MultipartFile thumbnail;
}
