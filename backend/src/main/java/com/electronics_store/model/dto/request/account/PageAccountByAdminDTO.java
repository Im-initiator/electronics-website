package com.electronics_store.model.dto.request.account;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PageAccountByAdminDTO {

    private int page = 1;
    private int limit = 10;

    @Min(value = 0, message = "status must be greater than or equal to 0")
    @Max(value = 1, message = "status must be less than or equal to 1")
    private int state = 1;

    @Min(value = 0, message = "user_status must be greater than or equal to 0")
    @JsonProperty(value = "user_status")
    private int userStatus;

    private String name;
}
