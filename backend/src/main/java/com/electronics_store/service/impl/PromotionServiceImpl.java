package com.electronics_store.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.mapper.PromotionMapper;
import com.electronics_store.model.entity.PromotionEntity;
import com.electronics_store.repository.PromotionRepository;
import com.electronics_store.repository.ShopRepository;
import com.electronics_store.utils.FileUtils;
import com.electronics_store.utils.RequestUtils;
import com.electronics_store.utils.ResponseUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.promotion.CreateAndUpdatePromotionByAdminDTO;
import com.electronics_store.service.PromotionService;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class PromotionServiceImpl implements PromotionService {

    PromotionRepository promotionRepository;
    PromotionMapper promotionMapper;
    ShopRepository shopRepository;
    @Override
    public ApiResponse<?> getPageByAdmin(Map<String, String> params) {

        try {
            State state = State.convert(Integer.parseInt(params.getOrDefault("state", "1")));
            if(!RequestUtils.isPageExists(params)&&!params.containsKey("name")&& params.containsKey("state")) {
                List<PromotionEntity> result = promotionRepository.findAllByState(state);
                return new ApiResponse<>(result, "Get all promotion successfully!");
            }
            Pageable pageable = RequestUtils.getPageable(params);
            Page<PromotionEntity> page = null;
            Map<String,Object> result = null;
            if (params.containsKey("name")) {
                String name = params.get("name");
                page = promotionRepository.findAllByStateAndNameContaining(state, name, pageable);
            }else {
                page = promotionRepository.findAllByState(state,pageable);
            }
            result = ResponseUtils.getPageResponse(pageable,page,promotionMapper,
                    (p) -> promotionMapper.toGetPromotionByAdminDTO((PromotionEntity) p));
            return new ApiResponse<>(result, "Get promotion successfully!");
        }catch (ClassCastException | NumberFormatException | NullPointerException e){
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            throw new CustomRuntimeException(ErrorSystem.PAGE_NOT_FOUND);
        }

    }

    @Override
    public ApiResponse<?> getOneByAdmin(Long id, int state) {
        State s = State.convert(state);
        PromotionEntity promotion = promotionRepository.findByIdAndState(id,s)
                .orElseThrow(() -> new CustomRuntimeException("Promotion not found", HttpStatus.NOT_FOUND));
        return new ApiResponse<>(promotionMapper.toGetPromotionByAdminDTO(promotion),"Get promotion successfully!");
    }

    @Override
    public ApiResponse<?> createByAdmin(CreateAndUpdatePromotionByAdminDTO promotionDTO) {
        PromotionEntity promotionEntity = promotionMapper.toEntity(promotionDTO);
        String path = null;
        try {
            path = FileUtils.saveImage(promotionDTO.getImage());
            promotionEntity.setImage(path);
            promotionEntity.setShop(shopRepository.getShop());
            promotionRepository.save(promotionEntity);
            return new ApiResponse<>("Create promotion successfully");
        }catch (Exception e){
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            if(path!=null) FileUtils.deleteImage(path);
            throw new CustomRuntimeException("Save image failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResponse<?> updateByAdmin(Long id, CreateAndUpdatePromotionByAdminDTO promotionDTO) {
        PromotionEntity promotionEntity = promotionRepository.findByIdAndState(id,State.ACTIVE)
                .orElseThrow(() -> new CustomRuntimeException("Promotion not found",HttpStatus.NOT_FOUND));
        promotionMapper.merge(promotionDTO,promotionEntity);
        String path = null;
        String oldPath = promotionEntity.getImage();
        try {
            path = FileUtils.saveImage(promotionDTO.getImage());
            promotionEntity.setImage(path);
            promotionRepository.save(promotionEntity);
            if(oldPath!=null&&FileUtils.checkPath(oldPath)){
                if(!FileUtils.deleteImage(oldPath)){
                    throw new CustomRuntimeException("Delete image failed",HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            return new ApiResponse<>("Update promotion successfully");
        }catch (Exception e){
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            if(path!=null) FileUtils.deleteImage(path);
            throw new CustomRuntimeException("Save promotion failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ApiResponse<?> updateByAdmin(Long id, State oldState, State newState) {
        PromotionEntity promotionEntity = promotionRepository.findByIdAndState(id,oldState)
                .orElseThrow(() -> new CustomRuntimeException("Promotion not found",HttpStatus.NOT_FOUND));
        promotionEntity.setState(newState);
        promotionRepository.save(promotionEntity);
        if (newState == State.ACTIVE)
            return new ApiResponse<>("Restore promotion successfully");
        else
            return new ApiResponse<>("Delete promotion successfully");
    }
}
