package com.electronics_store.model.dto.request.slide;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateAndUpdateSlideByAdminDTO {
    @Length(max = 100)
    @NotBlank
    private String name;
    @Length(max = 100)
    private String description;

    private MultipartFile image;
    @NotBlank
    private String link;
}
