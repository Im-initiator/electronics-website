package com.electronics_store.model.dto.response.brand;

import com.electronics_store.model.dto.response.BaseResponseByAdminDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBrandByAdmin extends BaseResponseByAdminDTO {
    private Long id;
    private String name;
    private String logo;
}
