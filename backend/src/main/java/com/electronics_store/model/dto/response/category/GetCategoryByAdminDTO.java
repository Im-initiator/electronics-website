package com.electronics_store.model.dto.response.category;

import com.electronics_store.model.dto.response.BaseResponseByAdminDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCategoryByAdminDTO extends BaseResponseByAdminDTO {
    private Long id;
    private String name;
    private String image;
}
