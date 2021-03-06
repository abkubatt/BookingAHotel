package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.enums.EStatusBooking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookHistoryDto {
    @JsonIgnore
    Long id;
    BookingDto booking;
    LocalDate changeDate;
    String comment;
    RoomDto room;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate checkInDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate checkOutDate;
    UserDto guest;
    UserDto user;
    @JsonIgnore
    boolean active;
    @JsonIgnore
    EStatusBooking statusBooking;

}
