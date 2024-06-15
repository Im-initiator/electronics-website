package com.electronics_store.controller.admin;

import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.service.CreateAndUpdateServiceByAdminDTO;
import com.electronics_store.service.ServiceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "admin/service", description = "Service Management")
@RestController
@RequestMapping("/admin/service")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class ShopServiceController {

    private final ServiceService service;

    @Operation(summary = "Get page", description = "params: page,limit,state,name(allow null)")
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllSlides(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok().body(service.getPageByAdmin(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPage(@PathVariable("id") Long id, @RequestParam("state") int state) {
        return ResponseEntity.ok().body(service.getOneByAdmin(id, state));
    }

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody CreateAndUpdateServiceByAdminDTO createAndUpdateServiceByAdminDTO) {
        return ResponseEntity.ok().body(service.createByAdmin(createAndUpdateServiceByAdminDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody CreateAndUpdateServiceByAdminDTO createAndUpdateServiceByAdminDTO) {
        return ResponseEntity.ok().body(service.updateByAdmin(id, createAndUpdateServiceByAdminDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.updateByAdmin(id, State.ACTIVE, State.DELETE));
    }

    @PatchMapping("/{id}/restore")
    public ResponseEntity<?> restore(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service.updateByAdmin(id, State.DELETE, State.ACTIVE));
    }
}
