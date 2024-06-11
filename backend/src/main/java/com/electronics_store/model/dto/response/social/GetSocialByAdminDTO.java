package com.electronics_store.model.dto.response.social;

import lombok.Data;

@Data
public class GetSocialByAdminDTO {

    private Long id;
    private String name;

    private String image;

    private String link;

    private int state;
}
