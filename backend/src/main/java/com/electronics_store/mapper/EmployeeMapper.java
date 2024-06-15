package com.electronics_store.mapper;

import com.electronics_store.model.dto.request.employee.CreateEmployeeByAdminDTO;
import com.electronics_store.model.entity.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeEntity toEntity(CreateEmployeeByAdminDTO createEmployeeByAdminDTO);
}
