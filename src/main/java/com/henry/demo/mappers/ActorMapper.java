package com.henry.demo.mappers;

import com.henry.demo.dto.ActorDTO;
import com.henry.demo.models.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    ActorDTO modelToDTO(Actor actor);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Actor dtoToModel(ActorDTO actorDTO);
}
