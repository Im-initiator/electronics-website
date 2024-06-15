package com.electronics_store.mapper;

import com.electronics_store.model.dto.response.role.GetRoleByAdminDTO;
import com.electronics_store.model.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    GetRoleByAdminDTO toGetRoleByAdminDTO(RoleEntity entity);
}
