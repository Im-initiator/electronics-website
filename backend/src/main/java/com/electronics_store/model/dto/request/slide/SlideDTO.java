package com.electronics_store.model.dto.request.slide;

import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class SlideDTO {
    private Long id;

    @Size(max = 100)
    private String name;

    @Size(max = 100)
    private String shortDescription;

    private String image;
    private String link;
}
