package com.electronics_store.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class DataMapper {
    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static <T> T toEntity(Object dto, Class<T> tClass) {
        return mapper.map(dto, tClass);
    }

    public static <T> T toDTO(Object entity, Class<T> tClass) {
        return mapper.map(entity, tClass);
    }
}
