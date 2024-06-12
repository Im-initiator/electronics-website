package com.electronics_store.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.electronics_store.model.dto.request.token.TokenDTO;
import com.electronics_store.model.dto.request.token.UpdateTokenByAdminDTO;
import com.electronics_store.model.entity.TokenEntity;

@Mapper(componentModel = "spring")
public interface TokenMapper extends StateMapper {
    TokenDTO toTokenDTO(TokenEntity tokenEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mergeUpdateTokenByAdminDTO(
            @MappingTarget TokenEntity tokenEntity, UpdateTokenByAdminDTO updateTokenByAdminDTO);
}
