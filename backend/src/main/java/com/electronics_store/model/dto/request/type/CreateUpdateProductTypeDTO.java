package com.electronics_store.model.dto.request.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUpdateProductTypeDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @JsonProperty("category_id")
    @NotNull(message = "Category id is required")
    private Long categoryId;

}
