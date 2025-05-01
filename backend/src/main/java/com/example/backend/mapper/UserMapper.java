package com.example.backend.mapper;

import com.example.backend.dto.UserResponse;
import com.example.backend.entity.UserEntity;

import com.example.backend.enums.UserRoleType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Named;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toDto(UserEntity userEntity);

    @Mapping(source = "roleType", target = "roleType", qualifiedByName = "roleTypeToString")
    UserResponse toUserResponse(UserEntity user);

    @Named("roleTypeToString")
    default String roleTypeToString(UserRoleType roleType) {
        return roleType != null ? roleType.name() : null;
    }
}
