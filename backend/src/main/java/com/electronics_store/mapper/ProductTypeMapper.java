package com.electronics_store.mapper;

import com.electronics_store.model.dto.request.type.CreateUpdateProductTypeDTO;
import com.electronics_store.model.dto.response.type.GetOneProductTypeByAdminDTO;
import com.electronics_store.model.dto.response.type.GetProductTypeByAdminDTO;
import com.electronics_store.model.entity.ProductTypeEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper extends StateMapper{

    ProductTypeEntity toEntity(CreateUpdateProductTypeDTO dto);
    GetProductTypeByAdminDTO toGetProductTypeByAdmin(ProductTypeEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mergeEntity(CreateUpdateProductTypeDTO dto,@MappingTarget ProductTypeEntity entity);

    GetOneProductTypeByAdminDTO toGetOneProductTypeByAdminDto(ProductTypeEntity productTypeEntity);

}
