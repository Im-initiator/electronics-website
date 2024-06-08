package com.electronics_store.model.dto.request.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ShopDTO {

    private Long id;

    private String name;

    private String phone;

    private String address;

    private String logo;

    private String map;
}
