package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.CityDto;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.enums.EBedType;
import com.example.bookinghotel.models.request.ToFiler;
import com.example.bookinghotel.models.response.HotelFilterResponse;
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

    ResponseEntity<?> Rating(List<HotelFilterResponse> hotelFilterResponsesList);

    List<HotelDto> findAllHotelsByCity(Long cityId);

    void countCurrentScore();

    List<HotelDto> findAll();

    ResponseEntity<?> filter(Long cityId, LocalDate checkInDate, LocalDate checkOutDate, EBedType bedType,int capacity);




}
