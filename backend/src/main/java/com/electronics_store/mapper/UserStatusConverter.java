package com.electronics_store.mapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import com.electronics_store.enums.UserStatus;

@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserStatus userStatus) {
        return userStatus.getValue();
    }

    @Override
    public UserStatus convertToEntityAttribute(Integer integer) {
        return UserStatus.convert(integer);
    }
}
