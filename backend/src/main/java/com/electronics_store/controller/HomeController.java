package com.electronics_store.controller;

import com.electronics_store.validator.FileNotEmpty;
import com.electronics_store.validator.ImageConstraint;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ResponseEntity<?> home() {
        System.out.println(SecurityContextHolder.getContext());
        return ResponseEntity.ok("OK");
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> homePost(@ImageConstraint @RequestPart("image") MultipartFile image) {
        System.out.println(SecurityContextHolder.getContext());
        return ResponseEntity.ok("OK");
    }
}
