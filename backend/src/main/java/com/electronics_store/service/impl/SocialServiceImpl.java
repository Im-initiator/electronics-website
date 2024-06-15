package com.electronics_store.service.impl;

import java.util.List;
import java.util.Map;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.electronics_store.enums.State;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.mapper.SocialMapper;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.slide.CreateAndUpdateSlideByAdminDTO;
import com.electronics_store.model.dto.request.social.CreateAndUpdateSocialDTO;
import com.electronics_store.model.dto.response.social.GetSocialByAdminDTO;
import com.electronics_store.model.entity.SocialEntity;
import com.electronics_store.repository.ShopRepository;
import com.electronics_store.repository.SocialRepository;
import com.electronics_store.service.SocialService;
import com.electronics_store.utils.FileUtils;
import com.electronics_store.utils.ResponseUtils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SocialServiceImpl implements SocialService {

    SocialRepository socialRepository;
    SocialMapper socialMapper;
    ShopRepository shopRepository;

    @Override
    public ApiResponse<?> findOneByAdmin(Long id, int state) {
        SocialEntity socialEntity = socialRepository
                .findOneByIdAndState(id, State.convert(state))
                .orElseThrow(() -> new CustomRuntimeException("Social not found"));
        return new ApiResponse<>(socialMapper.toGetSocialByAdminDTO(socialEntity), "Get social successfully");
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResponse<?> createSocialByAdmin(CreateAndUpdateSlideByAdminDTO createAndUpdateSlideByAdminDTO) {
        SocialEntity socialEntity = socialMapper.toSocialEntity(createAndUpdateSlideByAdminDTO);
        String path = null;
        try {
            if (createAndUpdateSlideByAdminDTO.getImage() != null) {
                path = FileUtils.saveImage(createAndUpdateSlideByAdminDTO.getImage());
                socialEntity.setImage(path);
            }
        } catch (Exception e) {
            if (path != null) FileUtils.deleteImage(path);
            throw new CustomRuntimeException("Save image failed");
        }

        socialEntity.setShop(shopRepository.getShop());
        socialRepository.save(socialEntity);
        return new ApiResponse<>("Save social successfully");
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResponse<?> updateSocialByAdmin(Long id, CreateAndUpdateSocialDTO createAndUpdateSocialDTO) {
        SocialEntity socialEntity = socialRepository
                .findOneByIdAndState(id, State.ACTIVE)
                .orElseThrow(() -> new CustomRuntimeException("Social not found"));
        socialMapper.mergerSocialEntity(socialEntity, createAndUpdateSocialDTO);
        String path = null;
        try {
            if (createAndUpdateSocialDTO.getImage() != null) {
                if (!FileUtils.isImageExisted(
                        createAndUpdateSocialDTO.getImage().getOriginalFilename())) {
                    path = FileUtils.saveImage(createAndUpdateSocialDTO.getImage());
                    if (socialEntity.getImage() != null && FileUtils.checkPath(socialEntity.getImage())) {
                        if (!FileUtils.deleteImage(socialEntity.getImage()))
                            throw new CustomRuntimeException("Delete image failed");
                    }
                    socialEntity.setImage(path);
                }
            }

            socialRepository.save(socialEntity);
            return new ApiResponse<>("Update social successfully");
        } catch (Exception e) {
            if (path != null) FileUtils.deleteImage(path);
            throw new CustomRuntimeException("Save image failed");
        }
    }

    @Override
    public ApiResponse<?> updateSocialByAdmin(Long id, State state) {
        SocialEntity socialEntity =
                socialRepository.findOneById(id).orElseThrow(() -> new CustomRuntimeException("Social not found"));
        socialEntity.setState(state);
        socialRepository.save(socialEntity);
        if (state == State.DELETE) {
            return new ApiResponse<>("Delete social successfully");
        }
        return new ApiResponse<>("Restore social successfully");
    }

    @Override
    public ApiResponse<?> findAllByAdmin(Map<String, String> params) {
        try {
            State state = State.convert(Integer.parseInt(params.getOrDefault("state", "1")));
            if (!params.containsKey("limit") && !params.containsKey("page") && !params.containsKey("name")&& params.containsKey("state")) {
                List<SocialEntity> list = socialRepository.findAllByState(state);
                List<GetSocialByAdminDTO> result =
                        list.stream().map(socialMapper::toGetSocialByAdminDTO).toList();
                return new ApiResponse<>(result, "Get all socials successfully!");
            }
            Page<SocialEntity> pageContent = null;
            int page = Integer.parseInt(params.get("page")) - 1;
            int limit = Integer.parseInt(params.get("limit"));
            Pageable pageable = PageRequest.of(page, limit);
            if (params.containsKey("name")) {
                pageContent = socialRepository.findAllByNameAndStateOrderByCreateDateDESC(
                        state, params.get("name"), pageable);
            } else {
                pageContent = socialRepository.findAllByStateOrderByCreateDateDesc(state, pageable);
            }
            Map<String, Object> result = ResponseUtils.getPageResponse(
                    pageable, pageContent, socialMapper, e -> socialMapper.toGetSocialByAdminDTO((SocialEntity) e));
            return new ApiResponse<>(result, "Get all slides successfully!");
        } catch (ClassCastException | NumberFormatException | NullPointerException e) {
            throw new CustomRuntimeException(ErrorSystem.PAGE_NOT_FOUND);
        }
    }
}
