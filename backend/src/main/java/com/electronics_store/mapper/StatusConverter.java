package com.electronics_store.mapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import com.electronics_store.enums.State;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<State, Integer> {
    @Override
    public Integer convertToDatabaseColumn(State state) {
        if (state == null) {
            state = State.ACTIVE;
        }
        return state.getValue();
    }

    @Override
    public State convertToEntityAttribute(Integer integer) {
        return State.convert(integer);
    }
}
