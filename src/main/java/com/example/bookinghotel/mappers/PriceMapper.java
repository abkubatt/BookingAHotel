package com.example.bookinghotel.mappers;

import com.example.bookinghotel.models.dtos.PriceDto;
import com.example.bookinghotel.models.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper extends BaseMapper<Price, PriceDto> {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);
}
