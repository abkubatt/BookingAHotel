package com.example.bookinghotel.mappers.enumMappers;

import com.example.bookinghotel.mappers.BaseMapper;
import com.example.bookinghotel.models.dtos.enumdtos.StatusOfUserDto;
import com.example.bookinghotel.models.entities.enumentities.StatusOfUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatusOfUserMapper extends BaseMapper<StatusOfUser, StatusOfUserDto> {
    StatusOfUserMapper INSTANCE = Mappers.getMapper(StatusOfUserMapper.class);
}
