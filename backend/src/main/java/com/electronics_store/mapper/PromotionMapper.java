package com.electronics_store.mapper;

import org.mapstruct.*;

import com.electronics_store.model.dto.request.promotion.CreateAndUpdatePromotionByAdminDTO;
import com.electronics_store.model.dto.response.promotion.GetPromotionByAdminDTO;
import com.electronics_store.model.entity.PromotionEntity;

@Mapper(componentModel = "spring")
public interface PromotionMapper extends StateMapper {

    GetPromotionByAdminDTO toGetPromotionByAdminDTO(PromotionEntity promotionEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "image", ignore = true)
    void merge(CreateAndUpdatePromotionByAdminDTO promotionDTO, @MappingTarget PromotionEntity promotionEntity);

    @Mapping(target = "image", ignore = true)
    PromotionEntity toEntity(CreateAndUpdatePromotionByAdminDTO promotionDTO);
}
