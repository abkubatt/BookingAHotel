package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.Review;
import com.example.bookinghotel.models.entities.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReplyToReviewDto {
    Long id;
    String text;
    UserDto user;
    LocalDate date;
    ReviewDto review;
}
