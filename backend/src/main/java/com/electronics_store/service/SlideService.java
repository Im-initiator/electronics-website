package com.electronics_store.service;

import java.util.Map;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.slide.CreateAndUpdateSlideByAdminDTO;

public interface SlideService {
    ApiResponse<?> getAllSlidesByAdmin(State state);

    ApiResponse<?> getAllSlidesByAdmin(Map<String, String> params);

    ApiResponse<?> updateSlideByAdmin(Long id, CreateAndUpdateSlideByAdminDTO slideDTO);

    ApiResponse<?> createSlideByAdmin(CreateAndUpdateSlideByAdminDTO slideDTO);

    ApiResponse<?> updateSlideByAdmin(Long id, State oldState, State newState);

    ApiResponse<?> getOneSlideByAdmin(Long id, int state);
}
