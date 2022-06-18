package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.enums.EStatusBooking;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingDto {
    Long id;
    HotelDto hotel;
    RoomDto room;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    UserDto guest;
    String comment;
    float priceOfBook;
    EStatusBooking statusBooking;

}
