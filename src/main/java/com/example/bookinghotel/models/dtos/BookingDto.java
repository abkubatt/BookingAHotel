package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.Room;
import com.example.bookinghotel.models.entities.User;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingDto {
    Long id;
    HotelDto hotel;
    RoomDto room;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    UserDto guest;
    String comment;
    boolean active;
    float priceOfBook;
}
