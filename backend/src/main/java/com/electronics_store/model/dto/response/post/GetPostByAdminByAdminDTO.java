package com.electronics_store.model.dto.response.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.electronics_store.model.dto.response.BaseResponseByAdminDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPostByAdminByAdminDTO extends BaseResponseByAdminDTO {
    private Long id;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Title is required")
    private String title;

    @JsonProperty("short_description")
    @NotBlank(message = "Short description is required")
    private String shortDescription;

    @NotBlank(message = "Content is required")
    private String content;

    @NotNull(message = "Thumbnail is required")
    private String thumbnail;
}
