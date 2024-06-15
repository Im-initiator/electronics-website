package com.electronics_store.service;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.branch.CreateAndUpdateBranchByAdminDTO;

import java.util.Map;

public interface BranchService {
    ApiResponse<?> getPageByAdmin(Map<String, String> params);

    ApiResponse<?> getOneByAdmin(Long id, int state);

    ApiResponse<?> createByAdmin(CreateAndUpdateBranchByAdminDTO postDTO);

    ApiResponse<?> updateByAdmin(Long id, CreateAndUpdateBranchByAdminDTO postDTO);

    ApiResponse<?> updateByAdmin(Long id, State oldState, State newState);

}
