package com.electronics_store.model.dto.request.account;

import java.util.Set;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAccountByAdmin {
    @NotNull(message = "State must be not null")
    @Min(value = 0, message = "State must be greater than 0")
    @Max(value = 1, message = "State must be less than 1")
    private Integer status;
}
