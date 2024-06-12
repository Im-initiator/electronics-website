package com.electronics_store.utils;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class RequestUtils {

    public static Pageable getPageable(Map<String, String> params) {
        int page = Integer.parseInt(params.get("page")) - 1;
        int limit = Integer.parseInt(params.get("limit"));
        return PageRequest.of(page, limit);
    }

    public static boolean isPageExists(Map<String, String> params) {
        return params.containsKey("page") && params.containsKey("limit");
    }

    public static boolean isNameExists(Map<String, String> params) {
        return params.containsKey("name");
    }
}
