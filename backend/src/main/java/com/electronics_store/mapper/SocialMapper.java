package com.electronics_store.mapper;

import com.electronics_store.model.dto.request.slide.CreateAndUpdateSlideByAdminDTO;
import com.electronics_store.model.dto.request.social.CreateAndUpdateSocialDTO;
import com.electronics_store.model.dto.response.social.GetSocialByAdminDTO;
import com.electronics_store.model.entity.SocialEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SocialMapper extends StateMapper{

    GetSocialByAdminDTO toGetSocialByAdminDTO(SocialEntity socialEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "image", ignore = true)
    void mergerSocialEntity(@MappingTarget SocialEntity socialEntity, CreateAndUpdateSocialDTO createAndUpdateSocialDTO);
    @Mapping(target = "image", ignore = true)
    SocialEntity toSocialEntity(CreateAndUpdateSlideByAdminDTO createAndUpdateSlideByAdminDTO);

}
