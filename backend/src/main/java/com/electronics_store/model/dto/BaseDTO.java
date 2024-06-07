package com.electronics_store.model.dto;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDTO {
    @NotNull(message = "Id must be not null")
    private Long id;

    @NotNull(message = "State must be not null")
    private Integer state;
}
