package com.electronics_store.controller.admin;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.shop.UpdateShopByAdminDTO;
import com.electronics_store.service.ShopService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "admin/shop", description = "Shop Management")
@RestController(value = "adminShopController")
@RequestMapping("/admin/shop")
public class ShopController {

    private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getShop() {
        return ResponseEntity.ok().body(shopService.getShop());
    }

    @PutMapping
    public ResponseEntity<ApiResponse<?>> updateShop(@Valid @ModelAttribute UpdateShopByAdminDTO shopUpdateDTO) {
        return ResponseEntity.ok().body(shopService.updateShop(shopUpdateDTO));
    }
}
