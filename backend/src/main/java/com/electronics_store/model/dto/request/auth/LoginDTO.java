package com.electronics_store.model.dto.request.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginDTO {
    @JsonProperty("user_name")
    private String userName;

    private String password;
}
