package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.BookingDto;
import org.springframework.http.ResponseEntity;

public interface BookingService {

    ResponseEntity<?> save(BookingDto bookingDto);

    ResponseEntity<?> update(BookingDto bookingDto);

    ResponseEntity<?> delete(BookingDto bookingDto);

    BookingDto findByIdSecond(Long id);

    ResponseEntity<?> cancelBooking(Long bookingId, String comment, Long userId);

    ResponseEntity<?> sendCode(String email);
    ResponseEntity<?> sendCode2(String email);


}
