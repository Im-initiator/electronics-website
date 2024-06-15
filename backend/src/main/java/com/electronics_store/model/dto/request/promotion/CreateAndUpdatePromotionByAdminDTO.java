package com.electronics_store.model.dto.request.promotion;

import com.electronics_store.validator.ImageConstraint;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CreateAndUpdatePromotionByAdminDTO {
    @NotBlank(message = "Name is required")
    private String name;

    private String content;
    private String description;

    @NotNull(message = "Percent is required")
    private Double percent;

    @NotNull(message = "Max discount is required")
    private Double maxDiscount;

    @NotNull(message = "Image is required")
    @ImageConstraint
    private MultipartFile image;
}
