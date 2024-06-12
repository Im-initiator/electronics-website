package com.electronics_store.controller.admin;

import java.util.Map;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.promotion.CreateAndUpdatePromotionByAdminDTO;
import com.electronics_store.service.PromotionService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@Tag(name = "admin/promotion")
@RestController(value = "admin/promotion")
@RequestMapping(value = "/admin/promotion")
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    @Operation(summary = "Get page", description = "params: page,limit,state,name")
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPage(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok().body(promotionService.getPageByAdmin(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id, @RequestParam("state") int state) {
        return ResponseEntity.ok().body(promotionService.getOneByAdmin(id, state));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@Valid @ModelAttribute CreateAndUpdatePromotionByAdminDTO promotionDTO) {
        return ResponseEntity.ok().body(promotionService.createByAdmin(promotionDTO));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(
            @PathVariable("id") Long id, @Valid @ModelAttribute CreateAndUpdatePromotionByAdminDTO promotionDTO) {
        return ResponseEntity.ok().body(promotionService.updateByAdmin(id, promotionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(promotionService.updateByAdmin(id, State.ACTIVE, State.DELETE));
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<?> restore(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(promotionService.updateByAdmin(id, State.DELETE, State.ACTIVE));
    }
}
