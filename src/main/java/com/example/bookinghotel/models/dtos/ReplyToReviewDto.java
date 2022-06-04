package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.User;
import lombok.Data;


@Data
public class ReplyToReviewDto {
    Long id;
    String text;
    UserDto user;
}
