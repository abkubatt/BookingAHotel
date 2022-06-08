package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.Booking;
import com.example.bookinghotel.models.entities.Room;
import com.example.bookinghotel.models.entities.User;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookHistoryDto {
    Long id;
    Booking booking;
    LocalDate changeDate;
    String comment;
    Room room;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    User guest;
    User user;
    boolean bookActive;
    boolean active;
}
