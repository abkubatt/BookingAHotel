package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.CityDto;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.request.ToFiler;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface HotelService {

    ResponseEntity<?> save(HotelDto hotelDto);

    ResponseEntity<?> update(HotelDto hotelDto);

    HotelDto findById(Long hotelId);

    ResponseEntity<?> delete(Long hotelId);
    ResponseEntity<?> blockHotel(Long hotelId);
    ResponseEntity<?> confirm(Long hotelId);

    ResponseEntity<?> findAllHotelsByRating(HotelDto hotelDto);

    List<HotelDto> findAllHotelsByCity(Long cityId);

    void countCurrentScore();

    List<HotelDto> findAll();

    ResponseEntity<?> filter(ToFiler filer);
}
