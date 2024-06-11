package com.electronics_store.service;

import java.util.Map;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.slide.CreateAndUpdateSlideByAdminDTO;
import com.electronics_store.model.dto.request.social.CreateAndUpdateSocialDTO;

public interface SocialService {

    ApiResponse<?> findOneByAdmin(Long id, int state);

    ApiResponse<?> createSocialByAdmin(CreateAndUpdateSlideByAdminDTO createAndUpdateSlideByAdminDTO);

    ApiResponse<?> updateSocialByAdmin(Long id, CreateAndUpdateSocialDTO createAndUpdateSocialDTO);

    ApiResponse<?> updateSocialByAdmin(Long id, State state);

    ApiResponse<?> findAllByAdmin(Map<String, String> params);
}
