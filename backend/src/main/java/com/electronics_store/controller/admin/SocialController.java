package com.electronics_store.controller.admin;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.request.slide.CreateAndUpdateSlideByAdminDTO;
import com.electronics_store.model.dto.request.social.CreateAndUpdateSocialDTO;
import com.electronics_store.service.SocialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/social")
@RequiredArgsConstructor
public class SocialController {

    private final SocialService socialService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id, @RequestParam int state ) {
        return ResponseEntity.ok(socialService.findOneByAdmin(id,state));
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam Map<String,String> params) {
        return ResponseEntity.ok(socialService.findAllByAdmin(params));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @ModelAttribute CreateAndUpdateSlideByAdminDTO createAndUpdateSlideByAdminDTO) {
        return ResponseEntity.ok().body(socialService.createSocialByAdmin(createAndUpdateSlideByAdminDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @ModelAttribute CreateAndUpdateSocialDTO createAndUpdateSocialDTO) {
        return ResponseEntity.ok().body(socialService.updateSocialByAdmin(id, createAndUpdateSocialDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(socialService.updateSocialByAdmin(id, State.DELETE));
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<?> restore(@PathVariable Long id) {
        return ResponseEntity.ok().body(socialService.updateSocialByAdmin(id, State.ACTIVE));
    }


}
