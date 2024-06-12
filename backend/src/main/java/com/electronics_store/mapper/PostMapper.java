package com.electronics_store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.electronics_store.model.dto.request.post.CreateAndUpdatePostDTO;
import com.electronics_store.model.dto.response.post.GetPostByAdminByAdminDTO;
import com.electronics_store.model.entity.PostEntity;

@Mapper(componentModel = "spring")
public interface PostMapper extends StateMapper {

    @Mapping(target = "thumbnail", ignore = true)
    @Mapping(source = "description", target = "shortDescription")
    PostEntity toPostEntity(CreateAndUpdatePostDTO createAndUpdatePostDTO);

    @Mapping(target = "thumbnail", ignore = true)
    void merger(CreateAndUpdatePostDTO createAndUpdatePostDTO, @MappingTarget PostEntity postEntity);

    GetPostByAdminByAdminDTO toGetPostByAdminDTO(PostEntity postEntity);
}
