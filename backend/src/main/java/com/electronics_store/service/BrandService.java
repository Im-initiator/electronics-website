package com.electronics_store.service;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.brand.CreateUpdateBrandDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface BrandService {
    ApiResponse<?> createByManager(MultipartFile logo, CreateUpdateBrandDTO createUpdateBrandDTO);


    ApiResponse<?> updateByManager(Long id,MultipartFile logo, CreateUpdateBrandDTO createUpdateBrandDTO);

    ApiResponse<?> updateByManager(Long id, State oldState, State newState);

    ApiResponse<?> getPageByManger(Map<String, String> params);

    ApiResponse<?> getOneByManager(Long id,int state);
}
