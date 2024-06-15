package com.electronics_store.mapper;

import com.electronics_store.model.dto.request.category.CreateUpdateCategoryDTO;
import com.electronics_store.model.dto.response.category.GetCategoryByAdminDTO;
import com.electronics_store.model.entity.CategoryEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends StateMapper{
    CategoryEntity toEntity(CreateUpdateCategoryDTO categoryDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void merge(CreateUpdateCategoryDTO categoryDTO,@MappingTarget CategoryEntity categoryEntity);

    GetCategoryByAdminDTO toGetCategoryByAdminDTO(CategoryEntity categoryEntity);

}
