package com.example.backend.mapper;

import com.example.backend.dto.UserResponse;
import com.example.backend.entity.UserEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-02T21:23:57+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 21.0.6 (Microsoft)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        if ( userEntity.getUserId() != null ) {
            userResponse.setUserId( userEntity.getUserId() );
        }
        userResponse.setUserName( userEntity.getUserName() );
        if ( userEntity.getRoleType() != null ) {
            userResponse.setRoleType( userEntity.getRoleType().name() );
        }

        return userResponse;
    }

    @Override
    public UserResponse toUserResponse(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setRoleType( roleTypeToString( user.getRoleType() ) );
        if ( user.getUserId() != null ) {
            userResponse.setUserId( user.getUserId() );
        }
        userResponse.setUserName( user.getUserName() );

        return userResponse;
    }
}
