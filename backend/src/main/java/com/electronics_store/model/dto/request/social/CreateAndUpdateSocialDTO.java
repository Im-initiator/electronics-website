package com.electronics_store.model.dto.request.social;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateAndUpdateSocialDTO {

    @Length(max = 50)
    private String name;

    private MultipartFile image;

    private String link;

}
