package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.ReplyToReview;
import com.example.bookinghotel.models.entities.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewDto {
    Long id;
    UserDto guest;
    HotelDto hotel;
    LocalDate date;
    double score;
    String text;
    boolean active;
}
