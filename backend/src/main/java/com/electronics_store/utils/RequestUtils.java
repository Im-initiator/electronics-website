package com.electronics_store.utils;

import java.util.Map;

import com.electronics_store.enums.State;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class RequestUtils {

    public static Pageable getPageable(Map<String, String> params) {
        int page = Integer.parseInt(params.getOrDefault("page","1")) - 1;
        int limit = Integer.parseInt(params.getOrDefault("limit","10"));
        return PageRequest.of(page, limit);
    }

    public static boolean isPageExists(Map<String, String> params) {
        return params.containsKey("page") && params.containsKey("limit");
    }

    public static State getState(Map<String, String> params){
        return State.convert(Integer.parseInt(params.getOrDefault("state","1")));
    }

    public static boolean isNameExists(Map<String, String> params) {
        return params.containsKey("name");
    }
}
