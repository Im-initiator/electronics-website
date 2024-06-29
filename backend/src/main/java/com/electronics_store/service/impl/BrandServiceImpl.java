package com.electronics_store.service.impl;

import com.electronics_store.enums.State;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.mapper.BrandMapper;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.brand.CreateUpdateBrandDTO;
import com.electronics_store.model.dto.response.brand.GetBrandByAdmin;
import com.electronics_store.model.entity.BrandEntity;
import com.electronics_store.model.entity.CategoryEntity;
import com.electronics_store.repository.BrandRepository;
import com.electronics_store.repository.CategoryRepository;
import com.electronics_store.service.BrandService;
import com.electronics_store.utils.FileUtils;
import com.electronics_store.utils.RequestUtils;
import com.electronics_store.utils.ResponseUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Slf4j
public class BrandServiceImpl implements BrandService {

    BrandRepository brandRepository;
    BrandMapper brandMapper;
    CategoryRepository categoryRepository;

    @Override
    public ApiResponse<?> createByManager(MultipartFile logo, CreateUpdateBrandDTO createUpdateBrandDTO) {
        if (brandRepository.isExistsBrandName(createUpdateBrandDTO.getName())){
            throw new CustomRuntimeException("Brand name is exists", HttpStatus.BAD_REQUEST);
        }
        CategoryEntity categoryEntity = categoryRepository.findByIdAndState(createUpdateBrandDTO.getCategoryId(), State.ACTIVE).orElseThrow(
                () -> new CustomRuntimeException("Category not found", HttpStatus.NOT_FOUND)
        );
        BrandEntity brandEntity = brandMapper.toEntity(createUpdateBrandDTO);
        brandEntity.setCategory(categoryEntity);
        String logoUrl=null;
        try {
            logoUrl = FileUtils.saveImage(logo);
            brandEntity.setLogo(logoUrl);
            brandRepository.save(brandEntity);
        }catch (Exception e){
            if (logoUrl != null){
                FileUtils.deleteImage(logoUrl);
            }
            throw  new CustomRuntimeException("Can not save image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ApiResponse<>("Brand created successfully");
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ApiResponse<?> updateByManager(Long id, MultipartFile logo, CreateUpdateBrandDTO createUpdateBrandDTO) {
        BrandEntity brandEntity = brandRepository.findByIdAndState(id, State.ACTIVE)
                .orElseThrow(() -> new CustomRuntimeException("Brand not found", HttpStatus.NOT_FOUND));
        if (!brandEntity.getName().equals(createUpdateBrandDTO.getName())){
            if (brandRepository.isExistsBrandName(createUpdateBrandDTO.getName())){
                throw new CustomRuntimeException("Brand name is exists", HttpStatus.BAD_REQUEST);
            }
        }
        CategoryEntity categoryEntity = categoryRepository.findByIdAndState(createUpdateBrandDTO.getCategoryId(), State.ACTIVE).orElseThrow(
                () -> new CustomRuntimeException("Category not found", HttpStatus.NOT_FOUND)
        );
        brandEntity.setCategory(categoryEntity);
        String oldLogo = brandEntity.getLogo();
        brandMapper.mergeEntity(createUpdateBrandDTO, brandEntity);
        String path = null;
        try {
            if (!FileUtils.isImageExisted(logo.getOriginalFilename())){
                path = FileUtils.saveImage(logo);
                brandEntity.setLogo(path);
            }
            brandRepository.save(brandEntity);
            if(path!=null && oldLogo != null && FileUtils.checkPath(oldLogo)){
                FileUtils.deleteImage(oldLogo);
            }
            return new ApiResponse<>("Brand updated successfully");
        }catch (Exception e){
            if (path!= null) {
                FileUtils.deleteImage(path);
            }
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            throw new CustomRuntimeException("Has error when update brand", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ApiResponse<?> updateByManager(Long id, State oldState, State newState) {
        BrandEntity brandEntity = brandRepository.findByIdAndState(id,oldState).orElseThrow(
                ()-> new CustomRuntimeException("Brand not found", HttpStatus.NOT_FOUND));
        brandEntity.setState(newState);
        brandRepository.save(brandEntity);
        if (newState == State.DELETE) {
            return new ApiResponse<>("Deleted successfully");
        }
        return new ApiResponse<>("Restore successfully");
    }

    @Override
    public ApiResponse<?> getPageByManger(Map<String, String> params) {
        try {
            State state = State.convert(Integer.parseInt(params.getOrDefault("state", "1")));
            if (!params.containsKey("name")&&!params.containsKey("page")&&!params.containsKey("limit") && params.containsKey("state")){
                List<BrandEntity> list = brandRepository.findByState(state);
                List<GetBrandByAdmin> result = list.stream().map(brandMapper::toGetBrandByAdmin).toList();
                return new ApiResponse<>(result,"Get brand successful");
            }

            Pageable pageable = RequestUtils.getPageable(params);
            Page<BrandEntity> page;
            if (params.containsKey("name")) {
                String name = params.get("name");
                page = brandRepository.getAllByStateAndName(state, name, pageable);
            }else{
                page = brandRepository.findAllByState(state, pageable);
            }
            Map<String,Object> result = ResponseUtils.getPageResponse(pageable, page, brandMapper,
                    (b) -> brandMapper.toGetBrandByAdmin((BrandEntity) b));
            return new ApiResponse<>(result, "Get brand successfully!");
        }catch (ClassCastException | NumberFormatException | NullPointerException e){
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            throw new CustomRuntimeException(ErrorSystem.PAGE_NOT_FOUND);
        }
    }

    @Override
    public ApiResponse<?> getOneByManager(Long id,int state) {
        State s = State.convert(state);
        BrandEntity brandEntity = brandRepository.getOneByIdAndStateIncludeCategory(id,s)
                .orElseThrow(()-> new CustomRuntimeException("Brand not found", HttpStatus.NOT_FOUND));
        return new ApiResponse<>(brandMapper.toGetOneBrandByAdminDto(brandEntity),"Get brand successfully!");
    }


}
