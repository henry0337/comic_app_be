package com.henry.demo.adapter.mapper;

import com.henry.demo.adapter.dto.ComicDTO;
import com.henry.demo.domain.model.Comic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComicMapper {
    ComicDTO modelToDTO(Comic comic);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Comic dtoToModel(ComicDTO comicDTO);
}
