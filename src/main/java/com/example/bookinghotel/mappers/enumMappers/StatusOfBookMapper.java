package com.example.bookinghotel.mappers.enumMappers;

import com.example.bookinghotel.mappers.BaseMapper;
import com.example.bookinghotel.models.dtos.enumdtos.StatusOfBookDto;
import com.example.bookinghotel.models.entities.enumentities.StatusOfBook;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatusOfBookMapper extends BaseMapper<StatusOfBook, StatusOfBookDto> {
    StatusOfBookMapper INSTANCE = Mappers.getMapper(StatusOfBookMapper.class);
}
