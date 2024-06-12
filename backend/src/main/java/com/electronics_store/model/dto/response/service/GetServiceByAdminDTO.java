package com.electronics_store.model.dto.response.service;

import com.electronics_store.model.dto.response.BaseResponseByAdminDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetServiceByAdminDTO extends BaseResponseByAdminDTO {
    private Long id;
    private String name;
    private String content;
}
