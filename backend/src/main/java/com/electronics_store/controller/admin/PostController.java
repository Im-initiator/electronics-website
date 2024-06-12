package com.electronics_store.controller.admin;

import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.electronics_store.enums.State;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.post.CreateAndUpdatePostDTO;
import com.electronics_store.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "admin/post", description = "Post Management")
@RestController(value = "admin/post")
@RequestMapping("/admin/post")
@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @Operation(summary = "Get page", description = "params: page,limit,state,title(allow null) or category(allow null)")
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPage(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok().body(postService.getPageByAdmin(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id, @RequestParam("state") int state) {
        return ResponseEntity.ok().body(postService.getOneByAdmin(id, state));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@Valid @ModelAttribute CreateAndUpdatePostDTO postDTO) {
        return ResponseEntity.ok().body(postService.createByAdmin(postDTO));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(
            @PathVariable("id") Long id, @Valid @ModelAttribute CreateAndUpdatePostDTO postDTO) {
        return ResponseEntity.ok().body(postService.updateByAdmin(id, postDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(postService.updateByAdmin(id, State.ACTIVE, State.DELETE));
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<?> restore(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(postService.updateByAdmin(id, State.DELETE, State.ACTIVE));
    }
}
