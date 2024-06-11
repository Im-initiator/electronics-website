package com.electronics_store.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.electronics_store.model.dto.request.shop.ShopDTO;
import com.electronics_store.model.dto.request.shop.UpdateShopByAdminDTO;
import com.electronics_store.model.entity.ShopEntity;

@Mapper(componentModel = "spring")
public interface ShopMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mergeEntity(UpdateShopByAdminDTO shopDTO, @MappingTarget ShopEntity shopEntity);

    ShopDTO toShopDTO(ShopEntity shopEntity);
}
