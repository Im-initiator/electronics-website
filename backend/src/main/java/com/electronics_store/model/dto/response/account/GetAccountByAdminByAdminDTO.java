package com.electronics_store.model.dto.response.account;

import java.util.Set;

import com.electronics_store.model.dto.request.role.RoleDTO;
import com.electronics_store.model.dto.response.BaseResponseByAdminDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAccountByAdminByAdminDTO extends BaseResponseByAdminDTO {
    private Long id;

    @JsonProperty("user_name")
    private String userName;

    private String email;

    private int status;

    @JsonProperty("full_name")
    private String fullName;

    private Set<RoleDTO> roles;
}
