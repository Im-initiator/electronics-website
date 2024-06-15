package com.electronics_store.controller.manager;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.brand.CreateUpdateBrandDTO;
import com.electronics_store.service.BrandService;
import com.electronics_store.validator.FileNotEmpty;
import com.electronics_store.validator.ImageConstraint;
import com.electronics_store.validator.groups.CreateValidation;
import com.electronics_store.validator.groups.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Tag(name = "manager/brand")
@RestController(value = "manager/brand")
@RequiredArgsConstructor
@RequestMapping("/manager/brand")
@Validated
public class BrandController {

    private final BrandService brandService;
    @Operation(summary = "Get page", description = "params: page(default 1),limit(default 10),state(default 1),name(allow null)")
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPage(@RequestParam Map<String,String> params){
        return ResponseEntity.ok().body(brandService.getPageByManger(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getOne(@PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok().body(brandService.getOneByManager(id));
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<?>> createBrand(
            @ImageConstraint @FileNotEmpty @RequestPart("logo") MultipartFile logo,
            @Validated(CreateValidation.class) @ModelAttribute CreateUpdateBrandDTO createUpdateBrandDTO) {
        return ResponseEntity.ok().body(brandService.createByManager(logo, createUpdateBrandDTO));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<?>> updateBrand(
           @PathVariable("id") @NotNull Long id,
           @ImageConstraint @FileNotEmpty @RequestPart("logo") MultipartFile logo,
           @Validated(UpdateValidation.class) @ModelAttribute CreateUpdateBrandDTO createUpdateBrandDTO) {
        return ResponseEntity.ok().body(brandService.updateByManager(id,logo, createUpdateBrandDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<?>> deleteBrand(@PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok().body(brandService.updateByManager(id, State.ACTIVE, State.DELETE));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<?>> restore(@PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok().body(brandService.updateByManager(id, State.DELETE, State.ACTIVE));
    }


}
