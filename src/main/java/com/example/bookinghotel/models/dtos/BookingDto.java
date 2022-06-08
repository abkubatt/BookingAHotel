package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.Room;
import com.example.bookinghotel.models.entities.User;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingDto {
    Long id;
    Hotel hotel;
    Room room;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    User guest;
    String comment;
    boolean active;
    float priceOfBook;
}
