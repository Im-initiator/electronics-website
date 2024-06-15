package com.electronics_store.controller.admin;

import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "admin/role",description = "Role management")
@RestController(value = "admin/role")
@RequestMapping("/admin/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll() {
        return ResponseEntity.ok().body(roleService.getAll());
    }
}
