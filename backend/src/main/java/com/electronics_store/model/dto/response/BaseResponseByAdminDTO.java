package com.electronics_store.model.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BaseResponseByAdminDTO {
    private int state;

    @JsonProperty("created_date")
    @JsonFormat(pattern = "HH:mm:ss-dd/MM/yyyy")
    private LocalDateTime createDate;

    @JsonProperty("modified_date")
    @JsonFormat(pattern = "HH:mm:ss-dd/MM/yyyy")
    private LocalDateTime modifyDate;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("last_modified_by")
    private String lastModifyBy;
}
