package com.electronics_store.model.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUpdateCategoryDTO {
    @NotBlank(message = "name must be not null")
    private String name;
}
