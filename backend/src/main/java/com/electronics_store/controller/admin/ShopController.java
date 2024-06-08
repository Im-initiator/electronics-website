package com.electronics_store.controller.admin;

import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.shop.UpdateShopByAdminDTO;
import com.electronics_store.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "adminShopController")
@RequestMapping("/admin/shop")
public class ShopController {

    private ShopService shopService;

    public ShopController(ShopService shopService){
        this.shopService = shopService;
    }

    @PutMapping
    public ResponseEntity<ApiResponse<?>> updateShop(@Valid @ModelAttribute UpdateShopByAdminDTO shopUpdateDTO){
        return ResponseEntity.ok().body(shopService.updateShop(shopUpdateDTO));
    }

}