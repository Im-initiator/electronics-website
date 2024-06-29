package com.electronics_store.service;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.type.CreateUpdateProductTypeDTO;

import java.util.Map;

public interface ProductTypeService {
    ApiResponse<?> createByManager(CreateUpdateProductTypeDTO request);

    ApiResponse<?> updateByManager(Long id,CreateUpdateProductTypeDTO request);
    ApiResponse<?> updateByManager(Long id, State oldState, State newState);

    ApiResponse<?> getPageByManager(Map<String, String> params);

    ApiResponse<?> getOneByManager(Long id,int state);
}
