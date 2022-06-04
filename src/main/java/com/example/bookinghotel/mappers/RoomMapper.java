package com.example.bookinghotel.mappers;

import com.example.bookinghotel.models.dtos.RoomDto;
import com.example.bookinghotel.models.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper extends BaseMapper<Room, RoomDto> {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
}
