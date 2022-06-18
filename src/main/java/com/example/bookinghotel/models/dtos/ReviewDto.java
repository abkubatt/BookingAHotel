package com.example.bookinghotel.models.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewDto {
    Long id;
    UserDto guest;
    HotelDto hotel;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
    double score;
    String text;
    boolean active;
}
