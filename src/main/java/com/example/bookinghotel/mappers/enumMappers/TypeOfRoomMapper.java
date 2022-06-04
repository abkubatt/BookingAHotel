package com.example.bookinghotel.mappers.enumMappers;

import com.example.bookinghotel.mappers.BaseMapper;
import com.example.bookinghotel.models.dtos.enumdtos.TypeOfRoomDto;
import com.example.bookinghotel.models.entities.enumentities.TypeOfRoom;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeOfRoomMapper extends BaseMapper<TypeOfRoom, TypeOfRoomDto> {
    TypeOfRoomMapper INSTANCE = Mappers.getMapper(TypeOfRoomMapper.class);
}
