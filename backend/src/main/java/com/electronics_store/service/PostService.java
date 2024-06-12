package com.electronics_store.service;

import java.util.Map;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.post.CreateAndUpdatePostDTO;

public interface PostService {
    ApiResponse<?> getPageByAdmin(Map<String, String> params);

    ApiResponse<?> getOneByAdmin(Long id, int state);

    ApiResponse<?> createByAdmin(CreateAndUpdatePostDTO createAndUpdatePostDTO);

    ApiResponse<?> updateByAdmin(Long id, CreateAndUpdatePostDTO createAndUpdatePostDTO);

    ApiResponse<?> updateByAdmin(Long id, State old, State newState);
}
