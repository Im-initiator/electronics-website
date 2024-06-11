package com.electronics_store.utils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ResponseUtils {

    public static <T> Map<String, Object> getPageResponse(
            Pageable pageable, Page<?> page, T t, Function<Object, Object> function) {
        List<?> data = page.getContent().stream().map(function).toList();
        return Map.of(
                "page", pageable.getPageNumber() + 1,
                "total_page", page.getTotalPages(),
                "total_items", page.getTotalElements(),
                "limit", pageable.getPageSize(),
                "items", data);
    }
}
