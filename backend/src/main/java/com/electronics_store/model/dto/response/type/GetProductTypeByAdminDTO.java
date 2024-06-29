package com.electronics_store.model.dto.response.type;

import com.electronics_store.model.dto.response.BaseResponseByAdminDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductTypeByAdminDTO extends BaseResponseByAdminDTO {
    private Long id;
    private String name;
}
