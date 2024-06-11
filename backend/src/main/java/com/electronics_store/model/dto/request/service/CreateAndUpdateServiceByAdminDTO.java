package com.electronics_store.model.dto.request.service;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateAndUpdateServiceByAdminDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Content is required")
    private String content;
}
