package com.electronics_store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.shop.ShopDTO;
import com.electronics_store.service.ShopService;

@RestController(value = "shopController")
@RequestMapping("/shop")
public class ShopController {

    private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ShopDTO>> getShop() {
        return ResponseEntity.ok(shopService.getShop());
    }
}
