package com.henry.demo.adapter.mapper;

import com.henry.demo.adapter.dto.GenreDTO;
import com.henry.demo.domain.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDTO modelToDTO(Genre genre);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Genre dtoToModel(GenreDTO genreDTO);
}
