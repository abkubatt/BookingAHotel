package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.CityDto;
import org.springframework.http.ResponseEntity;

public interface CityService {

    ResponseEntity<?> save(CityDto cityDto);
    
    ResponseEntity<?> update(CityDto cityDto);

    ResponseEntity<?> delete(Long cityId);

    CityDto findById(Long cityId);
    ResponseEntity<?> findAll();
}
