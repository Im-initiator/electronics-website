package com.electronics_store.service;

import java.util.Map;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.promotion.CreateAndUpdatePromotionByAdminDTO;

public interface PromotionService {
    ApiResponse<?> getPageByAdmin(Map<String, String> params);

    ApiResponse<?> getOneByAdmin(Long id, int state);

    ApiResponse<?> createByAdmin(CreateAndUpdatePromotionByAdminDTO promotionDTO);

    ApiResponse<?> updateByAdmin(Long id, CreateAndUpdatePromotionByAdminDTO promotionDTO);

    ApiResponse<?> updateByAdmin(Long id, State oldState, State newState);
}
