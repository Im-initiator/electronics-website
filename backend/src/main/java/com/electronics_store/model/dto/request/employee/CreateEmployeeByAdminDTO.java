package com.electronics_store.model.dto.request.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEmployeeByAdminDTO {

    private String address;

    @NotBlank(message = "Phone do not blank")
    private String phone;

    @NotNull(message = "Start date do not null")
    private LocalDate startDate;

    private LocalDate dateBirth;

    @NotNull(message = "Salary do not null")
    private Double salary;

    @JsonProperty("branch_id")
    @NotNull(message = "Branch id do not null")
    private Long branchId;
}
