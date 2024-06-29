package com.electronics_store.service.impl;

import com.electronics_store.enums.State;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.mapper.ProductTypeMapper;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.type.CreateUpdateProductTypeDTO;
import com.electronics_store.model.dto.response.type.GetOneProductTypeByAdminDTO;
import com.electronics_store.model.dto.response.type.GetProductTypeByAdminDTO;
import com.electronics_store.model.entity.CategoryEntity;
import com.electronics_store.model.entity.ProductTypeEntity;
import com.electronics_store.repository.CategoryRepository;
import com.electronics_store.repository.ProductTypeRepository;
import com.electronics_store.service.ProductTypeService;
import com.electronics_store.utils.RequestUtils;
import com.electronics_store.utils.ResponseUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Slf4j
public class ProductTypeServiceImpl implements ProductTypeService {

    ProductTypeRepository productTypeRepository;
    ProductTypeMapper productTypeMapper;
    CategoryRepository categoryRepository;
    @Override
    public ApiResponse<?> createByManager(CreateUpdateProductTypeDTO request) {
        ProductTypeEntity productTypeEntity = productTypeMapper.toEntity(request);
        CategoryEntity categoryEntity = categoryRepository.findByIdAndState(request.getCategoryId(), State.ACTIVE)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        productTypeEntity.setCategory(categoryEntity);
        productTypeRepository.save(productTypeEntity);
        return new ApiResponse<>("Create Successfully");
    }

    @Override
    public ApiResponse<?> updateByManager(Long id,CreateUpdateProductTypeDTO request) {
        ProductTypeEntity productTypeEntity = productTypeRepository.findByIdAndState(id, State.ACTIVE)
                .orElseThrow(() -> new CustomRuntimeException("Product Type not found", HttpStatus.NOT_FOUND));
        if(!request.getName().equals(productTypeEntity.getName())){
            if(productTypeRepository.isNameExist(request.getName())){
                throw new CustomRuntimeException("Product type name is exists",HttpStatus.BAD_REQUEST);
            }
        }
        CategoryEntity category = categoryRepository.findByIdAndState(request.getCategoryId(),State.ACTIVE).orElseThrow(
                ()-> new CustomRuntimeException("Category not found",HttpStatus.NOT_FOUND)
        );
        productTypeMapper.mergeEntity(request,productTypeEntity);
        productTypeEntity.setCategory(category);
        productTypeRepository.save(productTypeEntity);
        return new ApiResponse<>("Update successful");
    }

    @Override
    public ApiResponse<?> updateByManager(Long id, State oldState, State newState) {
        ProductTypeEntity productTypeEntity = productTypeRepository.findByIdAndState(id,oldState).orElseThrow(
                ()-> new CustomRuntimeException("Product type not fount",HttpStatus.NOT_FOUND)
        );
        productTypeEntity.setState(newState);
        productTypeRepository.save(productTypeEntity);
        if (newState == State.ACTIVE){
            return new ApiResponse<>("Restore successful");
        }
        return new ApiResponse<>("Delete successful");
    }

    @Override
    public ApiResponse<?> getPageByManager(Map<String, String> params) {
        try {
            Pageable pageable = RequestUtils.getPageable(params);
            State state = RequestUtils.getState(params);
            if (params.containsKey("state")&&!params.containsKey("name")&&!params.containsKey("page")){
                List<ProductTypeEntity> list = productTypeRepository.getListByState(state);
                List<GetProductTypeByAdminDTO> result = list.stream().map(productTypeMapper::toGetProductTypeByAdmin).toList();
                return new ApiResponse<>(result,"Get product type successful");
            }
            Page<ProductTypeEntity> page;
            if(params.containsKey("name")){
                String name = params.get("name");
                page = productTypeRepository.findAllByStateAndNameContaining(state,name,pageable);
            }else {
                page = productTypeRepository.findAllByState(state,pageable);
            }

            Map<String,Object> result = ResponseUtils.getPageResponse(pageable,page,productTypeMapper,
                     p -> productTypeMapper.toGetProductTypeByAdmin((ProductTypeEntity) p)
                    );
            return new ApiResponse<>(result,"Get product type successful");
        }catch (ClassCastException | NumberFormatException | NullPointerException e){
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            throw new CustomRuntimeException(ErrorSystem.PAGE_NOT_FOUND);
        }

    }

    @Override
    public ApiResponse<?> getOneByManager(Long id,int state) {
        State s = State.convert(state);
        ProductTypeEntity productTypeEntity = productTypeRepository.getOneByIdAndStateIncludeCategory(id,s).orElseThrow(
                () -> new CustomRuntimeException("Product type not found",HttpStatus.NOT_FOUND)
        );
        GetOneProductTypeByAdminDTO result = productTypeMapper.toGetOneProductTypeByAdminDto(productTypeEntity);
        return new ApiResponse<>(result,"Get product type successful");
    }


}
