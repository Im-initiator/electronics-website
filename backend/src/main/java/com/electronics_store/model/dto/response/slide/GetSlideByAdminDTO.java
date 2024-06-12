package com.electronics_store.model.dto.response.slide;

import com.electronics_store.model.dto.response.BaseResponseByAdminDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSlideByAdminDTO extends BaseResponseByAdminDTO {
    private Long id;
    private String name;
    private String shortDescription;
    private String image;
    private String link;
}
