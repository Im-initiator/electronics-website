package com.electronics_store.service;

import java.util.Map;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.service.CreateAndUpdateServiceByAdminDTO;

public interface ServiceService {
    ApiResponse<?> getPageByAdmin(Map<String, String> params);

    ApiResponse<?> getAllByAdmin(State state);

    ApiResponse<?> getOneByAdmin(Long id, int state);

    ApiResponse<?> createByAdmin(CreateAndUpdateServiceByAdminDTO createAndUpdateServiceByAdminDTO);

    ApiResponse<?> updateByAdmin(Long id, CreateAndUpdateServiceByAdminDTO createAndUpdateServiceByAdminDTO);

    ApiResponse<?> updateByAdmin(Long id, State old, State newState);
}
