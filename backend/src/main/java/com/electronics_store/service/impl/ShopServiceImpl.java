package com.electronics_store.service.impl;

import jakarta.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.mapper.ShopMapper;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.shop.ShopDTO;
import com.electronics_store.model.dto.request.shop.UpdateShopByAdminDTO;
import com.electronics_store.model.entity.ShopEntity;
import com.electronics_store.repository.ShopRepository;
import com.electronics_store.service.ShopService;
import com.electronics_store.utils.FileUtils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    ShopRepository shopRepository;
    ShopMapper shopMapper;

    @Cacheable(value = "shop", key = "'shop'")
    @Override
    public ApiResponse<ShopDTO> getShop() {
        ShopEntity shop = shopRepository.getShop();
        if (shop == null) {
            throw new CustomRuntimeException(ErrorSystem.INTERNAL_SERVER_ERROR);
        }
        return new ApiResponse<>(shopMapper.toShopDTO(shop), "Get shop successfully");
    }

    @Override
    @CacheEvict(value = "shop", key = "'shop'", allEntries = true)
    @Transactional(rollbackOn = Exception.class)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ApiResponse<?> updateShop(UpdateShopByAdminDTO shopUpdateDTO) {
        ShopEntity shop = shopRepository.getShop();
        String path = null;
        try {
            if (shop == null) {
                throw new CustomRuntimeException(ErrorSystem.INTERNAL_SERVER_ERROR);
            }
            shopMapper.mergeEntity(shopUpdateDTO, shop);
            // Kiểm tra xem file đã tồn tại hay chưa
            if (!FileUtils.isImageExisted(shopUpdateDTO.getImage().getOriginalFilename())) {
                path = FileUtils.saveImage(shopUpdateDTO.getImage());
                if (shop.getLogo() != null && FileUtils.checkPath(shop.getLogo())) {
                    if (!FileUtils.deleteImage(shop.getLogo())) {
                        throw new RuntimeException("Can't delete image");
                    }
                }
                shop.setLogo(path);
            }
            shopRepository.save(shop);
            return new ApiResponse<>("Update shop successfully");
        } catch (Exception e) {
            if (path != null) {
                FileUtils.deleteImage(path);
            }
            throw new CustomRuntimeException(ErrorSystem.SAVE_IMAGE_FAILED);
        }
    }
}
