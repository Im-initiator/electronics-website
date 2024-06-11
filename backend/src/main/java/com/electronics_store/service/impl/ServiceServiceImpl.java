package com.electronics_store.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.electronics_store.enums.State;
import com.electronics_store.exception.CustomRuntimeException;
import com.electronics_store.exception.ErrorSystem;
import com.electronics_store.mapper.ServiceMapper;
import com.electronics_store.model.dto.ApiResponse;
import com.electronics_store.model.dto.request.service.CreateAndUpdateServiceByAdminDTO;
import com.electronics_store.model.dto.response.service.GetServiceByAdminDTO;
import com.electronics_store.model.entity.ServiceEntity;
import com.electronics_store.repository.ServiceRepository;
import com.electronics_store.repository.ShopRepository;
import com.electronics_store.service.ServiceService;
import com.electronics_store.utils.ResponseUtils;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ServiceServiceImpl implements ServiceService {

    ServiceRepository serviceRepository;
    ServiceMapper serviceMapper;
    ShopRepository shopRepository;

    @Override
    public ApiResponse<?> getAllByAdmin(State state) {
        List<ServiceEntity> list = serviceRepository.findByState(state);
        List<GetServiceByAdminDTO> result =
                list.stream().map(serviceMapper::toGetServiceByAdminDTO).toList();
        return new ApiResponse<>(result, "Get all service successfully!");
    }

    @Override
    public ApiResponse<?> getPageByAdmin(Map<String, String> params) {
        try {
            if (!params.containsKey("page") && !params.containsKey("limit") && !params.containsKey("name")) {
                State state = State.convert(Integer.parseInt(params.get("state")));
                List<ServiceEntity> list = serviceRepository.findByState(state);
                List<GetServiceByAdminDTO> result =
                        list.stream().map(serviceMapper::toGetServiceByAdminDTO).toList();
                return new ApiResponse<>(result, "Get all service successfully!");
            }
            int page = Integer.parseInt(params.get("page")) - 1;
            int limit = Integer.parseInt(params.get("limit"));
            State state = State.convert(Integer.parseInt(params.get("state")));
            Pageable pageable = PageRequest.of(page, limit);
            Page<ServiceEntity> pageContent = null;
            if (params.containsKey("name")) {
                String name = params.get("name");
                pageContent = serviceRepository.findAllByNameAndStateOrderByCreateDateDESC(state, name, pageable);
            } else {
                pageContent = serviceRepository.findAllByStateOrderByCreateDateDesc(state, pageable);
            }
            Map<String, Object> result = ResponseUtils.getPageResponse(
                    pageable, pageContent, serviceMapper, e -> serviceMapper.toGetServiceByAdminDTO((ServiceEntity) e));

            return new ApiResponse<>(result, "Get all service successfully!");
        } catch (ClassCastException | NumberFormatException | NullPointerException e) {
            throw new CustomRuntimeException(ErrorSystem.PAGE_NOT_FOUND);
        }
    }

    @Override
    public ApiResponse<?> updateByAdmin(Long id, CreateAndUpdateServiceByAdminDTO serviceDTO) {
        ServiceEntity serviceEntity = serviceRepository
                .findByIdAndState(id, State.ACTIVE)
                .orElseThrow(() -> new CustomRuntimeException("Service not found!",HttpStatus.NOT_FOUND));
        serviceMapper.merge(serviceDTO, serviceEntity);
        serviceRepository.save(serviceEntity);
        return new ApiResponse<>("Update service successfully!");
    }

    @Override
    public ApiResponse<?> createByAdmin(CreateAndUpdateServiceByAdminDTO createAndUpdateServiceByAdminDTO) {
        ServiceEntity serviceEntity = serviceMapper.toServiceEntity(createAndUpdateServiceByAdminDTO);
        serviceEntity.setShop(shopRepository.getShop());
        serviceRepository.save(serviceEntity);
        return new ApiResponse<>("Create Service successfully!");
    }

    @Override
    public ApiResponse<?> updateByAdmin(Long id, State oldeState, State newState) {
        ServiceEntity ServiceEntity = serviceRepository
                .findByIdAndState(id, oldeState)
                .orElseThrow(() -> new CustomRuntimeException("Service not found!", HttpStatus.NOT_FOUND));
        ServiceEntity.setState(newState);
        serviceRepository.save(ServiceEntity);
        if (newState == State.ACTIVE) {
            return new ApiResponse<>("Restore Service successfully!");
        } else {
            return new ApiResponse<>("Delete Service successfully!");
        }
    }

    @Override
    public ApiResponse<?> getOneByAdmin(Long id, int state) {
        ServiceEntity ServiceEntity = serviceRepository
                .findByIdAndState(id, State.convert(state))
                .orElseThrow(() -> new CustomRuntimeException("Service not found!",HttpStatus.NOT_FOUND));
        return new ApiResponse<>(serviceMapper.toGetServiceByAdminDTO(ServiceEntity), "Get slide successfully!");
    }
}
