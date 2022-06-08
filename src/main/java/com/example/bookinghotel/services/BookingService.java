package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.BookingDto;
import org.springframework.http.ResponseEntity;

public interface BookingService {

    ResponseEntity<?> save(BookingDto bookingDto);

    ResponseEntity<?> update(BookingDto bookingDto);

    ResponseEntity<?> delete(BookingDto bookingDto);

    BookingDto findByIdSecond(Long id);

    ResponseEntity<?> cancelBooking(BookingDto bookingDto, String comment, Long userId);
}
