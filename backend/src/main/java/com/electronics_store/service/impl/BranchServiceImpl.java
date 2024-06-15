package com.electronics_store.service.impl;

import com.electronics_store.enums.State;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.mapper.BranchMapper;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.branch.CreateAndUpdateBranchByAdminDTO;
import com.electronics_store.model.entity.BranchEntity;
import com.electronics_store.model.entity.BrandEntity;
import com.electronics_store.repository.BranchRepository;
import com.electronics_store.repository.ShopRepository;
import com.electronics_store.service.BranchService;
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
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Slf4j
public class BranchServiceImpl implements BranchService {

    BranchRepository branchRepository;
    BranchMapper branchMapper;
    ShopRepository shopRepository;
    @Override
    public ApiResponse<?> getPageByAdmin(Map<String, String> params) {

        try {
            Pageable pageable = RequestUtils.getPageable(params);
            State state = State.convert(Integer.parseInt(params.getOrDefault("state", "1")));
            Page<BranchEntity> page = null;
            if (params.get("name") != null) {
                String name = params.get("name");
                page = branchRepository.getPageByStateAndNameContaining(state,name,pageable);

            }else if (params.get("province") != null) {
                String province = params.get("province");
                page = branchRepository.getPageByStateAndProvinceContaining(state,province,pageable);
            }else {
                page = branchRepository.getPageByState(state, pageable);
            }
            Map<String,Object> result = ResponseUtils.getPageResponse(pageable,page,branchMapper,
                    (b) -> branchMapper.toGetBranchByAdminDTO((BranchEntity) b));
            return new ApiResponse<>(result, "Get branch successfully!");
        }catch (ClassCastException | NumberFormatException | NullPointerException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            throw new CustomRuntimeException(ErrorSystem.PAGE_NOT_FOUND);
        }
    }

    @Override
    public ApiResponse<?> getOneByAdmin(Long id, int state) {
        BranchEntity branchEntity = branchRepository.findByIdAndState(id,State.convert(state))
                .orElseThrow(()-> new CustomRuntimeException("Branch not found", HttpStatus.NOT_FOUND));
        return new ApiResponse<>(branchMapper.toGetBranchByAdminDTO(branchEntity),"Get branch successfully!");
    }

    @Override
    public ApiResponse<?> createByAdmin(CreateAndUpdateBranchByAdminDTO branchDTO) {
        BranchEntity branchEntity = branchMapper.toBranchEntity(branchDTO);
        branchEntity.setShop(shopRepository.getShop());
        branchRepository.save(branchEntity);
        return new ApiResponse<>("Save branch successfully");
    }

    @Override
    public ApiResponse<?> updateByAdmin(Long id, CreateAndUpdateBranchByAdminDTO branchDTO) {
        BranchEntity branchEntity = branchRepository.findByIdAndState(id,State.ACTIVE)
                .orElseThrow(()-> new CustomRuntimeException("Branch not found!"));
        branchMapper.merge(branchDTO,branchEntity);
        branchRepository.save(branchEntity);
        return new ApiResponse<>("Update branch successfully");
    }

    @Override
    public ApiResponse<?> updateByAdmin(Long id, State oldState, State newState) {
        BranchEntity branchEntity = branchRepository.findByIdAndState(id,oldState)
                .orElseThrow(()-> new CustomRuntimeException("Branch not found!"));
        branchEntity.setState(newState);
        branchRepository.save(branchEntity);
        if (newState == State.DELETE) {
            return new ApiResponse<>("Delete branch successfully");
        }
        return new ApiResponse<>("Restore branch successfully");
    }
}
