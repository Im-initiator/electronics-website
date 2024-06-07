package com.electronics_store.model.dto.response.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountByUserResponseDTO {
    @JsonProperty("user_name")
    @NotBlank(message = "User name do not blank")
    private String userName;

    @Email(message = "Email not valid", regexp = "[A-Za-z0-9\\._%+\\-]+@[A-Za-z0-9\\.\\-]+\\.[A-Za-z]{2,}")
    @NotEmpty(message = "Email do not empty")
    private String email;

    @Size(max = 30, message = "Full name must not exceed 30 characters")
    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("image")
    private String thumbnail;
}
