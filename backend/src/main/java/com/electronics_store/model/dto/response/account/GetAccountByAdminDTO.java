package com.electronics_store.model.dto.response.account;

import java.time.LocalDateTime;
import java.util.Set;

import com.electronics_store.model.dto.request.role.RoleDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GetAccountByAdminDTO {
    private Long id;

    @JsonProperty("user_name")
    private String userName;

    private String email;

    private int state;

    private int status;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("create_date")
    private LocalDateTime createDate;

    @JsonProperty("modify_date")
    private LocalDateTime modifyDate;

    private Set<RoleDTO> roles;
}
