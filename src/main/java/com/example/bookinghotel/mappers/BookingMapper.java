package com.example.bookinghotel.mappers;

import com.example.bookinghotel.models.dtos.BookingDto;
import com.example.bookinghotel.models.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper extends BaseMapper<Booking, BookingDto> {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);
}
