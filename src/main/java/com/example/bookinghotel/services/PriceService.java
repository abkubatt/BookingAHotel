package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.PriceDto;
import com.example.bookinghotel.models.dtos.RoomCategoryDto;
import com.example.bookinghotel.models.entities.Price;
import com.example.bookinghotel.models.entities.RoomCategory;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface PriceService {

    PriceDto save(PriceDto priceDto);
    ResponseEntity<?> update(PriceDto priceDto);
    ResponseEntity<?> delete(Long priceId);
    PriceDto findPrice(RoomCategoryDto roomCategoryDto,LocalDate date);

    PriceDto findById(Long id);
}
