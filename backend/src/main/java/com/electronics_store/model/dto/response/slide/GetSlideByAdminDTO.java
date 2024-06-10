package com.electronics_store.model.dto.response.slide;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetSlideByAdminDTO {
    private Long id;
    private String name;
    private String shortDescription;
    private String image;
    private String link;
    @JsonProperty("create_date")
    private LocalDateTime createDate;
    @JsonProperty("modified_date")
    private LocalDateTime modifiedDate;

}
