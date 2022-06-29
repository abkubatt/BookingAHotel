package com.example.bookinghotel.mappers;

import com.example.bookinghotel.models.dtos.RoomCategoryDto;
import com.example.bookinghotel.models.entities.RoomCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.JpaRepository;
@Mapper
public interface RoomCategoryMapper extends BaseMapper<RoomCategory, RoomCategoryDto> {
    RoomCategoryMapper INSTANCE = Mappers.getMapper(RoomCategoryMapper.class);
}
