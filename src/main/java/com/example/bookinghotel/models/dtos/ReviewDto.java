package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.ReplyToReview;
import com.example.bookinghotel.models.entities.User;
import lombok.Data;


@Data
public class ReviewDto {
    Long id;
    UserDto user;
    HotelDto hotel;
    byte score;
    String text;
    ReplyToReviewDto replyToReview;
}
