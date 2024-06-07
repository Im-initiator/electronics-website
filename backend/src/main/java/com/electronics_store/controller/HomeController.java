package com.electronics_store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ResponseEntity<?> home() {
        System.out.println(SecurityContextHolder.getContext());
        return ResponseEntity.ok("OK");
    }
}
