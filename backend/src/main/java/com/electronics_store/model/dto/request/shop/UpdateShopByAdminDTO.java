package com.electronics_store.model.dto.request.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateShopByAdminDTO {

    private Long id;

    private String name;

    @Length(min = 7,max = 20, message = "Phone number must be between 7 and 20 numbers")
    private String phone;

    private String address;


    private MultipartFile image;

    @JsonProperty("google_map")
    @Length(max = 300, message = "Map must be at most 300 characters")
    private String map;
}
