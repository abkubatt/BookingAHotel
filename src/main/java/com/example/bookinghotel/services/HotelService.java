package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.HotelDto;
import org.springframework.http.ResponseEntity;

public interface HotelService {

    ResponseEntity<?> save(HotelDto hotelDto);

    ResponseEntity<?> update(HotelDto hotelDto);

    ResponseEntity<?> delete(HotelDto hotelDto);
}
