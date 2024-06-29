package com.electronics_store.mapper;

import com.electronics_store.model.dto.request.brand.CreateUpdateBrandDTO;
import com.electronics_store.model.dto.response.brand.GetBrandByAdmin;
import com.electronics_store.model.dto.response.brand.GetOneBrandByAdminDTO;
import com.electronics_store.model.entity.BrandEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BrandMapper extends StateMapper{

    BrandEntity toEntity(CreateUpdateBrandDTO brandDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mergeEntity(CreateUpdateBrandDTO brandDTO,@MappingTarget BrandEntity brandEntity);

    GetBrandByAdmin toGetBrandByAdmin(BrandEntity brandEntity);

    GetOneBrandByAdminDTO toGetOneBrandByAdminDto(BrandEntity brandEntity);

}
