package com.electronics_store.service.impl;

import java.util.List;
import java.util.Map;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.electronics_store.enums.State;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.mapper.PostMapper;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.post.CreateAndUpdatePostDTO;
import com.electronics_store.model.entity.PostEntity;
import com.electronics_store.repository.PostRepository;
import com.electronics_store.repository.ShopRepository;
import com.electronics_store.service.PostService;
import com.electronics_store.utils.FileUtils;
import com.electronics_store.utils.RequestUtils;
import com.electronics_store.utils.ResponseUtils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    ShopRepository shopRepository;
    PostMapper postMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Override
    public ApiResponse<?> getPageByAdmin(Map<String, String> params) {
        try {
            State state = State.convert(Integer.parseInt(params.getOrDefault("state", "1")));
            if (!RequestUtils.isPageExists(params) && !params.containsKey("title")) {
                List<PostEntity> result = postRepository.findAllByState(state);
                return new ApiResponse<>(result, "Get all post successfully!");
            }

            Pageable pageable = RequestUtils.getPageable(params);
            Page<PostEntity> page = null;
            if (params.containsKey("title")) {
                String title = params.get("title");
                page = postRepository.findAllByStateAndTitleContaining(state, title, pageable);
            } else if (params.containsKey("category")) {
                String category = params.get("category");
                page = postRepository.findAllByStateAndCategoryContaining(state, category, pageable);
            } else {
                page = postRepository.findAllByState(state, pageable);
            }
            Map<String, Object> result = ResponseUtils.getPageResponse(
                    pageable, page, postMapper, e -> postMapper.toGetPostByAdminDTO((PostEntity) e));
            return new ApiResponse<>(result, "Get all post successfully!");
        } catch (ClassCastException | NumberFormatException | NullPointerException e) {
            throw new CustomRuntimeException(ErrorSystem.PAGE_NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Override
    public ApiResponse<?> getOneByAdmin(Long id, int state) {
        State stateSave = State.convert(state);
        PostEntity postEntity = postRepository
                .findByIdAndState(id, stateSave)
                .orElseThrow(() -> new CustomRuntimeException("Post not found!", HttpStatus.NOT_FOUND));
        return new ApiResponse<>(postMapper.toGetPostByAdminDTO(postEntity), "Get post successfully!");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResponse<?> createByAdmin(CreateAndUpdatePostDTO createAndUpdatePostDTO) {
        String path = null;
        try {
            PostEntity postEntity = postMapper.toPostEntity(createAndUpdatePostDTO);
            path = FileUtils.saveImage(createAndUpdatePostDTO.getThumbnail());
            postEntity.setThumbnail(path);
            postEntity.setShop(shopRepository.getShop());
            postRepository.save(postEntity);
            return new ApiResponse<>("Create post successfully!");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error(e.getCause().getMessage(), e);
            if (path != null) {
                FileUtils.deleteImage(path);
            }
            throw new CustomRuntimeException("Create post failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Override
    public ApiResponse<?> updateByAdmin(Long id, CreateAndUpdatePostDTO createAndUpdatePostDTO) {
        PostEntity postEntity = postRepository
                .findByIdAndState(id, State.ACTIVE)
                .orElseThrow(() -> new CustomRuntimeException("Post not found!", HttpStatus.NOT_FOUND));
        postMapper.merger(createAndUpdatePostDTO, postEntity);
        String path = null;
        String oldPath = postEntity.getThumbnail();
        try {
            String fileName = createAndUpdatePostDTO.getThumbnail().getOriginalFilename();
            if (!FileUtils.isImageExisted(fileName)) {
                path = FileUtils.saveImage(createAndUpdatePostDTO.getThumbnail());
                postEntity.setThumbnail(path);
                postRepository.save(postEntity);
                if (oldPath != null && FileUtils.checkPath(oldPath)) {
                    FileUtils.deleteImage(oldPath);
                }
                return new ApiResponse<>("Update post successfully!");
            }
            postRepository.save(postEntity);
            return new ApiResponse<>("Update post successfully!");
        } catch (Exception e) {
            if (path != null) {
                FileUtils.deleteImage(path);
            }
            throw new CustomRuntimeException("Update post failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Override
    public ApiResponse<?> updateByAdmin(Long id, State old, State newState) {
        PostEntity postEntity = postRepository
                .findByIdAndState(id, old)
                .orElseThrow(() -> new CustomRuntimeException("Post not found!", HttpStatus.NOT_FOUND));
        postEntity.setState(newState);
        postRepository.save(postEntity);
        if (newState == State.ACTIVE) {
            return new ApiResponse<>("Restore post successfully!");
        }
        return new ApiResponse<>("Delete post successfully!");
    }
}
