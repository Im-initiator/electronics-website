package com.electronics_store.model.dto.response.type;

import com.electronics_store.model.dto.request.category.CategoryDTO;
import com.electronics_store.model.dto.response.BaseResponseByAdminDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOneProductTypeByAdminDTO extends BaseResponseByAdminDTO {
    private Long id;
    private String name;
    CategoryDTO category;
}
