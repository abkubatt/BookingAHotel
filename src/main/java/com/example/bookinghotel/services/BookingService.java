package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.BookingDto;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.request.ToCancelBooking;
import com.example.bookinghotel.models.request.ToSaveBooking;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    BookingDto save(BookingDto bookingDto);

    ResponseEntity<?> saveBooking(ToSaveBooking saveBooking);


    ResponseEntity<?> update(BookingDto bookingDto);

    ResponseEntity<?> delete(BookingDto bookingDto);

    BookingDto findByIdSecond(Long id);

    ResponseEntity<?> cancelBooking(ToCancelBooking cancelBooking);

    ResponseEntity<?> sendCode(String email);
    ResponseEntity<?> sendCode2(String email);
    BookingDto findByHotel(HotelDto hotelDto);
    List<BookingDto> findAllBooking(int numberOfPerson, LocalDate checkInDate, LocalDate checkOutDate);


}
