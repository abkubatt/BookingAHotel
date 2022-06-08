package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.Review;
import com.example.bookinghotel.models.entities.User;
import lombok.Data;

import java.time.LocalDate;


@Data
public class ReplyToReviewDto {
    Long id;
    String text;
    UserDto user;
    LocalDate date;
    ReviewDto review;
}
