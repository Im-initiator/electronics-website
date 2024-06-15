package com.electronics_store.model.dto.request.brand;

import com.electronics_store.validator.groups.CreateValidation;
import com.electronics_store.validator.groups.UpdateValidation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreateUpdateBrandDTO {
    @Null(message = "Id must be null", groups = {CreateValidation.class})
    @NotNull(message = "Id must not be null", groups = {UpdateValidation.class})
    private Long id;
    @NotNull(message = "Name must be not null",groups = {CreateValidation.class, UpdateValidation.class})
    private String name;
    @NotNull(message = "Category id must not be null",groups = {CreateValidation.class, UpdateValidation.class})
    private Long categoryId;
}
