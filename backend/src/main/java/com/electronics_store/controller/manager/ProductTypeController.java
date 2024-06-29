package com.electronics_store.controller.manager;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.type.CreateUpdateProductTypeDTO;
import com.electronics_store.service.ProductTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "manger/product type", description = "Product Type Management")
@RestController
@RequiredArgsConstructor
@RequestMapping("manager/product-type")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPage(@RequestParam Map<String,String> params){
        return ResponseEntity.ok().body(productTypeService.getPageByManager(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getOne(@PathVariable Long id, @RequestParam(value = "state",defaultValue = "1") int state){
        return ResponseEntity.ok().body(productTypeService.getOneByManager(id,state));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createByManger(@Valid @RequestBody CreateUpdateProductTypeDTO request){
        return ResponseEntity.ok().body(productTypeService.createByManager(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateByManager(@PathVariable("id") Long id,@Valid @RequestBody CreateUpdateProductTypeDTO request){
        return ResponseEntity.ok().body(productTypeService.updateByManager(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteByManager(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(productTypeService.updateByManager(id, State.ACTIVE,State.DELETE));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> restoreByManager(@PathVariable Long id){
        return ResponseEntity.ok().body(productTypeService.updateByManager(id, State.DELETE,State.ACTIVE));
    }


}
