package com.electronics_store.model.dto.request.account;

import java.util.Set;

import com.electronics_store.model.dto.request.role.RoleDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

    private Long id;

    @JsonProperty("user_name")
    private String userName;

    private String email;

    @JsonProperty("full_name")
    private String fullName;

    private Set<RoleDTO> roles;
}
