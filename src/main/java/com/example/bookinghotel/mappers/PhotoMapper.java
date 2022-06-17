package com.example.bookinghotel.mappers;

import com.example.bookinghotel.models.dtos.PhotoDto;
import com.example.bookinghotel.models.entities.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PhotoMapper extends BaseMapper<Photo, PhotoDto> {
    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);
}
