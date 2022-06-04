package com.example.bookinghotel.mappers;

import com.example.bookinghotel.models.dtos.ReviewDto;
import com.example.bookinghotel.models.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper extends BaseMapper<Review, ReviewDto> {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
}
