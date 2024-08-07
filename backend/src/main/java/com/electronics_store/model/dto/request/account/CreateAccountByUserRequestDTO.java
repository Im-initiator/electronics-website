package com.electronics_store.model.dto.request.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreateAccountByUserRequestDTO {

    @JsonProperty("user_name")
    @NotBlank(message = "user name do not blank")
    private String userName;

    @NotBlank(message = "password do not blank")
    @Size(min = 6, message = "password do not size < 6")
    private String password;

    @Email(message = "Email not valid", regexp = "[A-Za-z0-9\\._%+\\-]+@[A-Za-z0-9\\.\\-]+\\.[A-Za-z]{2,}")
    @NotEmpty(message = "email do not empty")
    private String email;
}
