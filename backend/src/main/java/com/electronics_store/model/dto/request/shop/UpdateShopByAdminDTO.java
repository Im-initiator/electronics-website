package com.electronics_store.model.dto.request.shop;

import com.electronics_store.validator.ImageConstraint;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UpdateShopByAdminDTO {

    private Long id;

    private String name;

    @Length(min = 7, max = 20, message = "Phone number must be between 7 and 20 numbers")
    private String phone;

    private String address;

    @ImageConstraint
    private MultipartFile image;

    @Length(max = 300, message = "Map must be at most 300 characters")
    private String map;
}
