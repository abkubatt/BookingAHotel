package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.PriceDto;
import org.springframework.http.ResponseEntity;

public interface PriceService {

    PriceDto save(PriceDto priceDto);
    ResponseEntity<?> update(PriceDto priceDto);
    ResponseEntity<?> delete(PriceDto priceDto);
}
