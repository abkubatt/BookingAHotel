package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.Booking;
import com.example.bookinghotel.models.entities.Room;
import com.example.bookinghotel.models.entities.User;
import com.example.bookinghotel.models.enums.EStatusBooking;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookHistoryDto {
    Long id;
    BookingDto booking;
    LocalDate changeDate;
    String comment;
    RoomDto room;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    UserDto guest;
    UserDto user;
    boolean active;
    EStatusBooking statusBooking;
}
