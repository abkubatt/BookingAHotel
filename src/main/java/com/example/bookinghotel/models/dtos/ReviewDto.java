package com.example.bookinghotel.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
    double score;
    String text;
    @JsonIgnore
    boolean active;
}
