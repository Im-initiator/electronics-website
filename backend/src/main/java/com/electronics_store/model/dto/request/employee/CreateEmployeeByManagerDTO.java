package com.electronics_store.model.dto.request.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEmployeeByManagerDTO {
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

    private String address;

    @NotBlank(message = "Phone do not blank")
    private String phone;

    @JsonProperty("start_date")
    @NotBlank(message = "Start date do not blank")
    private LocalDate startDate;

    @JsonProperty("date_birth")
    private LocalDate dateBirth;

    @NotNull(message = "Salary do not null")
    private Double salary;


}
