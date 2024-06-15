package com.electronics_store.mapper;

import com.electronics_store.model.dto.request.brand.CreateUpdateBrandDTO;
import com.electronics_store.model.dto.response.brand.GetBrandByAdmin;
import com.electronics_store.model.entity.BrandEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BrandMapper extends StateMapper{

    BrandEntity toEntity(CreateUpdateBrandDTO brandDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mergeEntity(CreateUpdateBrandDTO brandDTO,@MappingTarget BrandEntity brandEntity);

    GetBrandByAdmin toGetBrandByAdmin(BrandEntity brandEntity);

}
