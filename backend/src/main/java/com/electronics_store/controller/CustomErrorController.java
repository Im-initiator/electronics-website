package com.electronics_store.controller;

import java.util.Enumeration;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;

@RestController
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public void handleError(HttpServletRequest request) throws Throwable {

        Enumeration<String> enumeration = request.getAttributeNames();
        if (request.getAttribute("jakarta.servlet.error.exception") != null) {
            throw (Throwable) request.getAttribute("jakarta.servlet.error.exception");
        }
        throw new CustomRuntimeException(ErrorSystem.RESOURCE_NOTFOUND);
    }
}
