package com.electronics_store.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public void handleError(HttpServletRequest request) throws Throwable {
        if (request.getAttribute("jakarta.servlet.error.exception") != null) {
            throw (Throwable) request.getAttribute("jakarta.servlet.error.exception");
        }
        throw new Exception("Server has some error");
    }
}
