package com.henry.demo.mappers;

import com.henry.demo.dto.GenreDTO;
import com.henry.demo.models.Genre;
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
