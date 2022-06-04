package com.example.bookinghotel.mappers;

import com.example.bookinghotel.models.dtos.CityDto;
import com.example.bookinghotel.models.entities.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper extends BaseMapper<City, CityDto> {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
}
