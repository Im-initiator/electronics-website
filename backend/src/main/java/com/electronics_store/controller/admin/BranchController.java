package com.electronics_store.controller.admin;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.branch.CreateAndUpdateBranchByAdminDTO;
import com.electronics_store.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "admin/branch")
@RestController(value = "admin/branch")
@RequestMapping("/admin/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @Operation(summary = "Get page", description = "params: page(default 1),limit(default 10),state(default 1),name(allow null),province(allow null)")
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPage(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok().body(branchService.getPageByAdmin(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id, @RequestParam("state") int state) {
        return ResponseEntity.ok().body(branchService.getOneByAdmin(id, state));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateAndUpdateBranchByAdminDTO branchDTO) {
        return ResponseEntity.ok().body(branchService.createByAdmin(branchDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id, @Valid @RequestBody CreateAndUpdateBranchByAdminDTO branchDTO) {
        return ResponseEntity.ok().body(branchService.updateByAdmin(id, branchDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(branchService.updateByAdmin(id, State.ACTIVE, State.DELETE));
    }

    @PatchMapping("/{id}/restore")
    public ResponseEntity<?> restore(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(branchService.updateByAdmin(id, State.DELETE, State.ACTIVE));
    }
    


    
}
