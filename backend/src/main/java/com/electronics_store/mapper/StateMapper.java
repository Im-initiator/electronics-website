package com.electronics_store.mapper;

import org.mapstruct.Mapper;

import com.electronics_store.enums.State;

@Mapper(componentModel = "spring")
public interface StateMapper {

    default int fromState(State state) {
        return state.getValue();
    }
}
