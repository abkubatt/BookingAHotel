package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.dtos.enumdtos.StatusOfBookDto;
import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.Room;
import com.example.bookinghotel.models.entities.User;
import com.example.bookinghotel.models.entities.enumentities.StatusOfBook;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class BookingDto {
    Long id;
    HotelDto hotel;
    RoomDto room;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate checkInDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate checkOutDate;
    UserDto user;
    String comment;
//    StatusOfBookDto statusOfBook;
    boolean active;
    float priceOfBook;
}
