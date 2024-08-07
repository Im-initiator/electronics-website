package com.electronics_store.mapper;

import com.electronics_store.model.dto.request.account.CreateAccountByAdminRequestDTO;
import org.mapstruct.Mapper;

import com.electronics_store.enums.UserStatus;
import com.electronics_store.model.dto.request.account.AccountDTO;
import com.electronics_store.model.dto.response.account.GetAccountByAdminByAdminDTO;
import com.electronics_store.model.entity.AccountEntity;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper extends StateMapper {

    GetAccountByAdminByAdminDTO toGetAccountByAdminDTO(AccountEntity accountEntity);

    AccountDTO toAccountDTO(AccountEntity accountEntity);

    @Mapping(target = "employee", ignore = true)
    AccountEntity toEntity(CreateAccountByAdminRequestDTO createAccountByAdminRequestDTO);

    default int fromState(UserStatus status) {
        return status.getValue();
    }
}
