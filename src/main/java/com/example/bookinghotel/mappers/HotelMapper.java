package com.example.bookinghotel.mappers;

import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.entities.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HotelMapper extends BaseMapper<Hotel, HotelDto> {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);
}
