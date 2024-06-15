package com.electronics_store.mapper;

import com.electronics_store.model.dto.request.branch.CreateAndUpdateBranchByAdminDTO;
import com.electronics_store.model.dto.response.branch.GetBranchByAdminDTO;
import com.electronics_store.model.entity.BranchEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BranchMapper extends StateMapper{
    GetBranchByAdminDTO toGetBranchByAdminDTO(BranchEntity branchEntity);
    BranchEntity toBranchEntity(CreateAndUpdateBranchByAdminDTO createAndUpdateBranchByAdminDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void  merge(CreateAndUpdateBranchByAdminDTO createAndUpdateBranchByAdminDTO,@MappingTarget BranchEntity branchEntity);

}
