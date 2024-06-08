package com.electronics_store.model.dto.request.token;

import lombok.Data;

@Data
public class UpdateTokenByAdminDTO {
    private boolean expired = false;
    private boolean revoked = false;
}
