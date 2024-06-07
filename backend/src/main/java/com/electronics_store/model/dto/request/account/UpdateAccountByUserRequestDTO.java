package com.electronics_store.model.dto.request.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountByUserRequestDTO {

    @JsonProperty("id")
    private Long id;

    @NotBlank(message = "User name do not blank")
    private String userName;

    @Email(message = "Email not valid", regexp = "[A-Za-z0-9\\._%+\\-]+@[A-Za-z0-9\\.\\-]+\\.[A-Za-z]{2,}")
    @NotEmpty(message = "Email do not empty")
    private String email;

    @Size(max = 30, message = "Full name must not exceed 30 characters")
    @JsonProperty("full_name")
    private String fullName;

    private MultipartFile image;
}
