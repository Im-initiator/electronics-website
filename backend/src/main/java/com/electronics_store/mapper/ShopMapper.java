package com.electronics_store.mapper;

import com.electronics_store.model.dto.request.shop.UpdateShopByAdminDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

import com.electronics_store.model.dto.request.shop.ShopDTO;
import com.electronics_store.model.entity.ShopEntity;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ShopMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mergeEntity(UpdateShopByAdminDTO shopDTO, @MappingTarget ShopEntity shopEntity);

    ShopDTO toShopDTO(ShopEntity shopEntity);
}
