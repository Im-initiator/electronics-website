package com.electronics_store.mapper;

import com.electronics_store.model.dto.response.slide.GetSlideByAdminDTO;
import com.electronics_store.model.dto.request.slide.CreateAndUpdateSlideByAdminDTO;
import com.electronics_store.model.entity.SlideEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SlideMapper extends StateMapper{

    GetSlideByAdminDTO toGetSlideByAdminDTO(SlideEntity slideEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "image",ignore = true)
    @Mapping(source = "description",target = "shortDescription")
    void mergerSlideEntity(CreateAndUpdateSlideByAdminDTO createAndUpdateSlideByAdminDTO, @MappingTarget SlideEntity slideEntity);

    @Mapping(target = "image",ignore = true)
    @Mapping(source = "description",target = "shortDescription")
    SlideEntity toEntity(CreateAndUpdateSlideByAdminDTO createAndUpdateSlideByAdminDTO);


}
