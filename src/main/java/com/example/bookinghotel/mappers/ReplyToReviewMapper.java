package com.example.bookinghotel.mappers;

import com.example.bookinghotel.models.dtos.ReplyToReviewDto;
import com.example.bookinghotel.models.entities.ReplyToReview;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReplyToReviewMapper extends BaseMapper<ReplyToReview, ReplyToReviewDto> {
    ReplyToReviewMapper INSTANCE = Mappers.getMapper(ReplyToReviewMapper.class);
}
