package com.electronics_store.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.electronics_store.model.dto.request.service.CreateAndUpdateServiceByAdminDTO;
import com.electronics_store.model.dto.response.service.GetServiceByAdminDTO;
import com.electronics_store.model.entity.ServiceEntity;

@Mapper(componentModel = "spring")
public interface ServiceMapper extends StateMapper {
    GetServiceByAdminDTO toGetServiceByAdminDTO(ServiceEntity serviceEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void merge(
            CreateAndUpdateServiceByAdminDTO createAndUpdateServiceByAdminDTO,
            @MappingTarget ServiceEntity serviceEntity);

    ServiceEntity toServiceEntity(CreateAndUpdateServiceByAdminDTO createAndUpdateServiceByAdminDTO);
}
