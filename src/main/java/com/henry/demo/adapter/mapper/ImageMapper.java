package com.henry.demo.adapter.mapper;

import com.henry.demo.adapter.dto.ImageDTO;
import com.henry.demo.adapter.dto.request.ImageRequest;
import com.henry.demo.adapter.helper.ImageMapperHelper;
import com.henry.demo.domain.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ImageMapperHelper.class})
public interface ImageMapper {

    @Mapping(target = "episode", source = "episodeId")
    ImageDTO modelToDTO(Image image);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "episodeId", source = "episode.id")
    @Mapping(target = "createdAt", ignore = true)
    Image dtoToModel(ImageDTO imageDTO);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Image requestToModel(ImageRequest request);
}

