package com.electronics_store.controller;

import java.util.Enumeration;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "error", description = "Error Handling don't care")
@Hidden
@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping("/error")
    public void handleError(HttpServletRequest request) throws Throwable {

        Enumeration<String> enumeration = request.getAttributeNames();
        if (request.getAttribute("jakarta.servlet.error.exception") != null) {
            throw (Throwable) request.getAttribute("jakarta.servlet.error.exception");
        }
        throw new CustomRuntimeException(ErrorSystem.RESOURCE_NOTFOUND);
    }
}
