package com.henry.demo.adapter.mapper;

import com.henry.demo.adapter.dto.UserDTO;
import com.henry.demo.adapter.dto.request.UserRequest;
import com.henry.demo.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "email", expression = "java(user.getUsername())")
    UserDTO modelToDTO(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isCredentialsExpired", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isAccountLocked", ignore = true)
    @Mapping(target = "isAccountExpired", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User dtoToModel(UserDTO userDTO);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isCredentialsExpired", ignore = true)
    @Mapping(target = "isAccountLocked", ignore = true)
    @Mapping(target = "isAccountExpired", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User requestToModel(UserRequest request);
}
