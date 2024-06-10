package com.electronics_store.controller.admin;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.slide.CreateAndUpdateSlideByAdminDTO;
import com.electronics_store.service.SlideService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@PreAuthorize("hasAnyAuthority('ADMIN')")
@RestController
@RequestMapping("/admin/slide")
@RequiredArgsConstructor
public class SlideController {

    private final SlideService slideService;

//    @GetMapping
//    public ResponseEntity<ApiResponse<?>> getAllSlides(@RequestParam("state") int state){
//        return ResponseEntity.ok().body(slideService.getAllSlidesByAdmin(State.convert(state)));
//    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllSlides(@RequestParam Map<String, String> params){
        return ResponseEntity.ok().body(slideService.getAllSlidesByAdmin(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSlide(@PathVariable("id") Long id, @RequestParam("state") int state){
        return ResponseEntity.ok().body(slideService.getOneSlideByAdmin(id,state));
    }

    @PostMapping
    public ResponseEntity<?> createSlide(@Valid @ModelAttribute CreateAndUpdateSlideByAdminDTO slideDTO){
        return ResponseEntity.ok().body(slideService.createSlideByAdmin(slideDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSlide(@PathVariable("id") Long id, @Valid @ModelAttribute CreateAndUpdateSlideByAdminDTO slideDTO){
        return ResponseEntity.ok().body(slideService.updateSlideByAdmin(id, slideDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSlide(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(slideService.updateSlideByAdmin(id, State.ACTIVE, State.DELETE));
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<?> restore(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(slideService.updateSlideByAdmin(id, State.DELETE, State.ACTIVE));
    }




}
