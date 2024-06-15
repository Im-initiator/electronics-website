package com.electronics_store.service.impl;

import com.electronics_store.enums.State;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.mapper.CategoryMapper;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.category.CreateUpdateCategoryDTO;
import com.electronics_store.model.entity.CategoryEntity;
import com.electronics_store.repository.CategoryRepository;
import com.electronics_store.service.CategoryService;
import com.electronics_store.utils.FileUtils;
import com.electronics_store.utils.RequestUtils;
import com.electronics_store.utils.ResponseUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;


    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResponse<?> createByManager(MultipartFile image, CreateUpdateCategoryDTO createUpdateCategoryDTO) {
        if (categoryRepository.isNameExists(createUpdateCategoryDTO.getName())){
            throw new CustomRuntimeException("Category name is exists");
        }
        String path=null;
        CategoryEntity categoryEntity = categoryMapper.toEntity(createUpdateCategoryDTO);
        try {
            path = FileUtils.saveImage(image);
            categoryEntity.setImage(path);
            categoryRepository.save(categoryEntity);
            return new ApiResponse<>("Category created successfully");
        }catch (Exception e){
            if (path != null){
                FileUtils.deleteImage(path);
            }
            throw new CustomRuntimeException("Can not save image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ApiResponse<?> updateByManager(Long id, MultipartFile image, CreateUpdateCategoryDTO createUpdateCategoryDTO) {
        CategoryEntity categoryEntity = categoryRepository.findByIdAndState(id, State.ACTIVE).orElseThrow(
                () -> new CustomRuntimeException("Category not found", HttpStatus.NOT_FOUND)
        );
        if (!categoryEntity.getName().equals(createUpdateCategoryDTO.getName())){
            if(categoryRepository.isNameExists(createUpdateCategoryDTO.getName())){
                throw new CustomRuntimeException("Category name is exists", HttpStatus.BAD_REQUEST);
            }
        }
        categoryMapper.merge(createUpdateCategoryDTO,categoryEntity);
        String path = null;
        String oldPath = categoryEntity.getImage();
        boolean isImageExists = FileUtils.isImageExisted(image.getOriginalFilename());
        try {
            if(!isImageExists){
                path = FileUtils.saveImage(image);
                categoryEntity.setImage(path);
            }
            categoryRepository.save(categoryEntity);
            if (!isImageExists&&oldPath!=null&&FileUtils.checkPath(oldPath)){
                FileUtils.deleteImage(oldPath);
            }
            return new ApiResponse<>("Category updated successfully");
        }catch (Exception e){
            if (path != null){
                FileUtils.deleteImage(path);
            }
            throw new CustomRuntimeException("Can not save category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ApiResponse<?> updateByManager(Long id, State oldState, State newState) {
        CategoryEntity categoryEntity = categoryRepository.findByIdAndState(id, oldState).orElseThrow(
                () -> new CustomRuntimeException("Category not found", HttpStatus.NOT_FOUND)
        );
        categoryEntity.setState(newState);
        categoryRepository.save(categoryEntity);
        return new ApiResponse<>("Category updated successfully");
    }

    @Override
    public ApiResponse<?> getOneByManager(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findByIdAndState(id, State.ACTIVE).orElseThrow(
                () -> new CustomRuntimeException("Category not found", HttpStatus.NOT_FOUND)
        );
        return new ApiResponse<>(categoryMapper.toGetCategoryByAdminDTO(categoryEntity),"Category found successfully");
    }

    @Override
    public ApiResponse<?> getPageByManger(Map<String, String> params) {
        try {
            Pageable pageable = RequestUtils.getPageable(params);
            State state = State.convert(Integer.parseInt(params.getOrDefault("state","1")));
            Page<CategoryEntity> page;
            if(params.containsKey("name")){
                String name = params.get("name");
                page = categoryRepository.findAllByStateAndNameContaining(state,name,pageable);
            }else {
                page = categoryRepository.findAllByState(state,pageable);
            }
            Map<String,Object> result = ResponseUtils.getPageResponse(pageable,page,categoryMapper,
                    e -> categoryMapper.toGetCategoryByAdminDTO((CategoryEntity) e));
            return new ApiResponse<>(result,"Get all category successfully");
        }catch (ClassCastException | NumberFormatException | NullPointerException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            throw new CustomRuntimeException(ErrorSystem.PAGE_NOT_FOUND);
        }
    }
}
