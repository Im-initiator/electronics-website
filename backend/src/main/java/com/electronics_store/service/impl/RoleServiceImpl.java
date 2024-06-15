package com.electronics_store.service.impl;

import com.electronics_store.mapper.RoleMapper;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.response.role.GetRoleByAdminDTO;
import com.electronics_store.model.entity.RoleEntity;
import com.electronics_store.repository.RoleRepository;
import com.electronics_store.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public ApiResponse<?> getAll() {
        Set<RoleEntity> roleEntities = roleRepository.getAll();
        Set<GetRoleByAdminDTO> roles = roleEntities.stream().map(roleMapper::toGetRoleByAdminDTO).collect(Collectors.toSet());
        return new ApiResponse<>(roles,"Get all roles successfully");
    }
}
