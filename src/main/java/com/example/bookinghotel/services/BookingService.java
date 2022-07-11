package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.BookingDto;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.RoomDto;
import com.example.bookinghotel.models.enums.EStatusBooking;
import com.example.bookinghotel.models.request.ToCancelBooking;
import com.example.bookinghotel.models.request.ToSaveBooking;
import com.example.bookinghotel.models.response.ResponseEmail;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    BookingDto save(BookingDto bookingDto);

    ResponseEntity<?> saveBooking(ToSaveBooking saveBooking);


    ResponseEntity<?> update(BookingDto bookingDto);

    ResponseEntity<?> delete(BookingDto bookingDto);

    BookingDto findById(Long id);

    ResponseEntity<?> cancelBooking(ToCancelBooking cancelBooking);

    ResponseEntity<?> sendCode(String email, ResponseEmail responseEmail);
    ResponseEntity<?> sendCode2(String email,ResponseEmail responseEmail);
    List<BookingDto> findAllByHotel(Long hotelId);
    List<BookingDto> findAllBooking(Long hotelId, int numberOfPerson, LocalDate checkInDate, LocalDate checkOutDate);

    List<BookingDto> findAllByRoomAndActive(RoomDto roomDto, EStatusBooking statusBooking);

}
