package com.alexandru.obaj.soa.user.bl;

import com.alexandru.obaj.soa.user.dl.model.UserEntity;
import com.alexandru.obaj.soa.user.sl.model.UserDto;

public class UserConverter {

    public static UserEntity toEntity(UserDto dto) {
        return new UserEntity(0, dto.getUserId(), dto.getFirstName(), dto.getLastName(), dto.getPassword(), dto.getEmail());
    }

    public static UserDto fromEntity(UserEntity entity) {
        return new UserDto(entity.getUserId(), entity.getFirstName(), entity.getLastName(), entity.getPassword(), entity.getEmail());
    }
}
