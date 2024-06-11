package com.electronics_store.service;

import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.shop.ShopDTO;
import com.electronics_store.model.dto.request.shop.UpdateShopByAdminDTO;

public interface ShopService {
    ApiResponse<ShopDTO> getShop();

    ApiResponse<?> updateShop(UpdateShopByAdminDTO shopUpdateDTO);
}
