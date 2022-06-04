package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.Room;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class PriceDto {
    Long id;
    float price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
    RoomDto room;
}
