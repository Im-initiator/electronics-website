package com.electronics_store.model.dto.request.slide;

import com.electronics_store.validator.ImageConstraint;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CreateAndUpdateSlideByAdminDTO {
    @Length(max = 100)
    @NotBlank
    private String name;

    @Length(max = 100)
    private String description;

    @NotNull
    @ImageConstraint
    private MultipartFile image;

    @NotBlank
    private String link;
}
