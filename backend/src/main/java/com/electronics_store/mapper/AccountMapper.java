package com.electronics_store.mapper;

import org.mapstruct.Mapper;

import com.electronics_store.enums.UserStatus;
import com.electronics_store.model.dto.request.account.AccountDTO;
import com.electronics_store.model.dto.response.account.GetAccountByAdminByAdminDTO;
import com.electronics_store.model.entity.AccountEntity;

@Mapper(componentModel = "spring")
public interface AccountMapper extends StateMapper {

    GetAccountByAdminByAdminDTO toGetAccountByAdminDTO(AccountEntity accountEntity);

    AccountDTO toAccountDTO(AccountEntity accountEntity);

    default int fromState(UserStatus status) {
        return status.getValue();
    }
}
