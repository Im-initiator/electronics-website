package com.electronics_store.model.dto.response.branch;

import com.electronics_store.model.dto.response.BaseResponseByAdminDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBranchByAdminDTO extends BaseResponseByAdminDTO {
    private String name;
    private String address;
    private String phone;
    @JsonProperty("google_map")
    private String googleMap;
    private String province;
}
