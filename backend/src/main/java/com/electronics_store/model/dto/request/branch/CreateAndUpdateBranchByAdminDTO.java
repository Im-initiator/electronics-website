package com.electronics_store.model.dto.request.branch;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAndUpdateBranchByAdminDTO {
    @NotBlank(message = "Branch name is required")
    private String name;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Please input phone")
    private String phone;
    @NotBlank(message = "Please input google map")
    @JsonProperty("google_map")
    private String googleMap;
    @NotBlank(message = "Please input province")
    private String province;
}
