package com.henry.demo.adapter.mapper;

import com.henry.demo.adapter.dto.EpisodeDTO;
import com.henry.demo.adapter.dto.request.EpisodeRequest;
import com.henry.demo.adapter.helper.ComicMapperHelper;
import com.henry.demo.domain.model.Episode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ComicMapperHelper.class})
public interface EpisodeMapper {

    @Mapping(target = "comic", source = "comicId")
    EpisodeDTO modelToDTO(Episode episode);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comicId", source = "comic.id")
    Episode dtoToModel(EpisodeDTO episodeDTO);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Episode requestToModel(EpisodeRequest request);
}

