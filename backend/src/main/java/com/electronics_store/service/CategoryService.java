package com.electronics_store.service;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.category.CreateUpdateCategoryDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CategoryService {
    ApiResponse<?> createByManager(MultipartFile image, CreateUpdateCategoryDTO createUpdateCategoryDTO);

    ApiResponse<?> updateByManager(Long id, MultipartFile image, CreateUpdateCategoryDTO createUpdateCategoryDTO);

    ApiResponse<?> updateByManager(Long id, State oldState, State newState);

    ApiResponse<?> getOneByManager(Long id,int state);

    ApiResponse<?> getPageByManger(Map<String, String> params);
}
