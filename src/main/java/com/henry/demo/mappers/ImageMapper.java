package com.henry.demo.mappers;

import com.henry.demo.dto.ImageDTO;
import com.henry.demo.models.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "episode", source = "episodeId")
    ImageDTO modelToDTO(Image image);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "episodeId", source = "episode.id")
    @Mapping(target = "createdAt", ignore = true)
    Image dtoToModel(ImageDTO imageDTO);
}
