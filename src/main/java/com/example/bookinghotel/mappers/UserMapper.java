package com.example.bookinghotel.mappers;

import com.example.bookinghotel.models.dtos.UserDto;
import com.example.bookinghotel.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDto> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
