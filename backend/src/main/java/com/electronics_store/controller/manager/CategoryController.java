package com.electronics_store.controller.manager;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.category.CreateUpdateCategoryDTO;
import com.electronics_store.service.CategoryService;
import com.electronics_store.validator.FileNotEmpty;
import com.electronics_store.validator.ImageConstraint;
import com.electronics_store.validator.groups.CreateValidation;
import com.electronics_store.validator.groups.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Tag(name = "manager/category")
@RestController
@RequiredArgsConstructor
@RequestMapping("/manager/category")
@Validated
public class CategoryController {


    private final CategoryService categoryService;
    @Operation(summary = "Get page", description = "params: page(default 1),limit(default 10),state(default 1),name(allow null)")
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPage(@RequestParam Map<String,String> params){
        return ResponseEntity.ok().body(categoryService.getPageByManger(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getOne(@PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok().body(categoryService.getOneByManager(id));
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<?>> create(
            @ImageConstraint @FileNotEmpty @RequestPart("image") MultipartFile image,
            @Validated @ModelAttribute CreateUpdateCategoryDTO CreateUpdateCategoryDTO) {
        return ResponseEntity.ok().body(categoryService.createByManager(image, CreateUpdateCategoryDTO));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<?>> update(
            @PathVariable("id") @NotNull(message = "id must be not null") Long id,
            @ImageConstraint @FileNotEmpty @RequestPart("image") MultipartFile image,
            @Validated @ModelAttribute CreateUpdateCategoryDTO CreateUpdateCategoryDTO) {
        return ResponseEntity.ok().body(categoryService.updateByManager(id,image, CreateUpdateCategoryDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok().body(categoryService.updateByManager(id, State.ACTIVE, State.DELETE));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<?>> restore(@PathVariable("id") @NotNull Long id) {
        return ResponseEntity.ok().body(categoryService.updateByManager(id, State.DELETE, State.ACTIVE));
    }
    
}
