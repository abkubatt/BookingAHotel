package com.example.bookinghotel.models.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class BookHistoryDto {
    Long id;
    BookingDto booking;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate changeDate;
    String comment;
    boolean active;
}
