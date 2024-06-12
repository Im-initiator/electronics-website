package com.electronics_store.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.electronics_store.enums.State;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.mapper.SlideMapper;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.slide.CreateAndUpdateSlideByAdminDTO;
import com.electronics_store.model.dto.response.slide.GetSlideByAdminDTO;
import com.electronics_store.model.entity.SlideEntity;
import com.electronics_store.repository.ShopRepository;
import com.electronics_store.repository.SlideRepository;
import com.electronics_store.service.SlideService;
import com.electronics_store.utils.FileUtils;
import com.electronics_store.utils.RequestUtils;
import com.electronics_store.utils.ResponseUtils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

    SlideRepository slideRepository;
    SlideMapper slideMapper;
    ShopRepository shopRepository;

    @Override
    public ApiResponse<?> getAllSlidesByAdmin(State state) {
        List<SlideEntity> list = slideRepository.findByState(state);
        List<GetSlideByAdminDTO> result =
                list.stream().map(slideMapper::toGetSlideByAdminDTO).toList();
        return new ApiResponse<>(result, "Get all slides successfully!");
    }

    @Override
    public ApiResponse<?> getAllSlidesByAdmin(Map<String, String> params) {
        try {
            State state = State.convert(Integer.parseInt(params.getOrDefault("state", "1")));
            if (!params.containsKey("page") && !params.containsKey("limit") && !params.containsKey("name")) {
                List<SlideEntity> list = slideRepository.findByState(state);
                List<GetSlideByAdminDTO> result =
                        list.stream().map(slideMapper::toGetSlideByAdminDTO).toList();
                return new ApiResponse<>(result, "Get all slides successfully!");
            }
            Pageable pageable = RequestUtils.getPageable(params);
            Page<SlideEntity> pageContent = null;
            if (params.containsKey("name")) {
                String name = params.get("name");
                pageContent = slideRepository.findAllByNameAndStateOrderByCreateDateDESC(state, name, pageable);
            } else {
                pageContent = slideRepository.findAllByStateOrderByCreateDateDesc(state, pageable);
            }
            Map<String, Object> result = ResponseUtils.getPageResponse(
                    pageable, pageContent, slideMapper, e -> slideMapper.toGetSlideByAdminDTO((SlideEntity) e));
            return new ApiResponse<>(result, "Get all slides successfully!");
        } catch (ClassCastException | NumberFormatException | NullPointerException e) {
            throw new CustomRuntimeException(ErrorSystem.PAGE_NOT_FOUND);
        }
    }

    @Override
    public ApiResponse<?> updateSlideByAdmin(Long id, CreateAndUpdateSlideByAdminDTO slideDTO) {
        SlideEntity slideEntity = slideRepository
                .findByIdAndState(id, State.ACTIVE)
                .orElseThrow(() -> new CustomRuntimeException("Slide not found!"));
        slideMapper.mergerSlideEntity(slideDTO, slideEntity);
        String path = null;
        try {
            if (!FileUtils.isImageExisted(slideDTO.getImage().getOriginalFilename())) {
                path = FileUtils.saveImage(slideDTO.getImage());
                if (FileUtils.checkPath(slideEntity.getImage())) {
                    if (!FileUtils.deleteImage(slideEntity.getImage())) {
                        throw new CustomRuntimeException("Can't delete old image!");
                    }
                }
                slideEntity.setImage(path);
            }
            slideRepository.save(slideEntity);
            return new ApiResponse<>("Update slide successfully!");
        } catch (Exception e) {
            if (path != null) {
                FileUtils.deleteImage(path);
            }
            throw new CustomRuntimeException("Update slide failed!");
        }
    }

    @Override
    public ApiResponse<?> createSlideByAdmin(CreateAndUpdateSlideByAdminDTO slideDTO) {
        SlideEntity slideEntity = slideMapper.toEntity(slideDTO);
        String path = null;
        try {
            path = FileUtils.saveImage(slideDTO.getImage());
            slideEntity.setImage(path);
            slideEntity.setShop(shopRepository.getShop());
            slideRepository.save(slideEntity);
        } catch (Exception e) {
            e.printStackTrace();
            if (path != null) {
                FileUtils.deleteImage(path);
            }
            throw new CustomRuntimeException("Create slide failed!");
        }
        return new ApiResponse<>("Create slide successfully!");
    }

    @Override
    public ApiResponse<?> updateSlideByAdmin(Long id, State oldeState, State newState) {
        SlideEntity slideEntity = slideRepository
                .findByIdAndState(id, oldeState)
                .orElseThrow(() -> new CustomRuntimeException("Slide not found!"));
        slideEntity.setState(newState);
        slideRepository.save(slideEntity);
        if (newState == State.ACTIVE) {
            return new ApiResponse<>("Restore slide successfully!");
        } else {
            return new ApiResponse<>("Delete slide successfully!");
        }
    }

    @Override
    public ApiResponse<?> getOneSlideByAdmin(Long id, int state) {
        SlideEntity slideEntity = slideRepository
                .findByIdAndState(id, State.convert(state))
                .orElseThrow(() -> new CustomRuntimeException("Slide not found!"));
        return new ApiResponse<>(slideMapper.toGetSlideByAdminDTO(slideEntity), "Get slide successfully!");
    }
}
