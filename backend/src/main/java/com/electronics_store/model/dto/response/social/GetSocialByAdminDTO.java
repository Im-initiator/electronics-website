package com.electronics_store.model.dto.response.social;

import com.electronics_store.model.dto.response.BaseResponseByAdminDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetSocialByAdminDTO extends BaseResponseByAdminDTO {

    private Long id;
    private String name;

    private String image;

    private String link;
}
