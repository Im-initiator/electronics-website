package com.electronics_store.model.dto.request.shop;

import lombok.Data;

@Data
public class ShopDTO {

    private Long id;

    private String name;

    private String phone;

    private String address;

    private String logo;

    private String map;
}
