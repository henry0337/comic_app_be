package com.henry.demo.mappers;

import com.henry.demo.dto.EpisodeDTO;
import com.henry.demo.models.Episode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EpisodeMapper {

    @Mapping(target = "comic", source = "comicId")
    EpisodeDTO modelToDTO(Episode episode);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comicId", source = "comic.id")
    Episode dtoToModel(EpisodeDTO episodeDTO);
}
