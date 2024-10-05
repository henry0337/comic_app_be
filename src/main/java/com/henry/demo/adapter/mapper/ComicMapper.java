package com.henry.demo.adapter.mapper;

import com.henry.demo.adapter.dto.ActorDTO;
import com.henry.demo.domain.model.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComicMapper {
    ActorDTO modelToDTO(Actor actor);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Actor dtoToModel(ActorDTO actorDTO);
}
