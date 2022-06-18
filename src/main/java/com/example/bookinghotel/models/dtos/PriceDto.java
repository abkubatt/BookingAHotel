package com.example.bookinghotel.models.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceDto {
    Long id;
    float price;
    LocalDate startDate;
    LocalDate endDate;
    RoomDto room;
    boolean active;
}
