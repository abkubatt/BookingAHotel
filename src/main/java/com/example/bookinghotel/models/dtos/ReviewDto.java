package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.ReplyToReview;
import com.example.bookinghotel.models.entities.User;
import lombok.Data;

import java.time.LocalDate;


@Data
public class ReviewDto {
    Long id;
    UserDto guest;
    HotelDto hotel;
    LocalDate date;
    byte score;
    String text;
    boolean active;
}
