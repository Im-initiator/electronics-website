package com.electronics_store.model.dto.response.account;

import java.util.List;

import com.electronics_store.model.dto.request.account.AccountDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListAccountByAdminDTO {

    @JsonProperty("total_page")
    private int totalPage;
    @JsonProperty("total_items")
    private long totalItems;
    private int page;
    private int limit;
    private List<AccountDTO> accounts;
}
