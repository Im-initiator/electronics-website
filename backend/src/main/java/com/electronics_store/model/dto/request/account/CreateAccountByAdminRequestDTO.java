package com.electronics_store.model.dto.request.account;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreateAccountByAdminRequestDTO {
    @JsonProperty("user_name")
    @NotBlank(message = "User name do not blank")
    private String userName;

    @NotBlank(message = "password do not blank")
    @Size(min = 6, message = "Minimum password is 6 characters")
    private String password;

    @Email(message = "Email not valid", regexp = "[A-Za-z0-9\\._%+\\-]+@[A-Za-z0-9\\.\\-]+\\.[A-Za-z]{2,}")
    @NotEmpty(message = "Email do not empty")
    private String email;

    @Size(max = 30, message = "Full name must not exceed 30 characters")
    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("role_ids")
    private Set<Long> roleIds;
}
