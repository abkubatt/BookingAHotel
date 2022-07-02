package com.example.bookinghotel.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReplyToReviewDto {
    @JsonIgnore
    Long id;
    String text;
    UserDto user;
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
    ReviewDto review;
}
