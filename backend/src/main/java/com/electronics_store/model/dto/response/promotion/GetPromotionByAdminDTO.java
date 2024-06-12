package com.electronics_store.model.dto.response.promotion;

import com.electronics_store.model.dto.response.BaseResponseByAdminDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPromotionByAdminDTO extends BaseResponseByAdminDTO {
    private String name;

    private String content;

    private String description;

    private String image;

    private Double percent;

    @JsonProperty("max_discount")
    private Double maxDiscount;
}
