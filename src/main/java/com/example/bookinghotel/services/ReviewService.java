package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.ReviewDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {

    ResponseEntity<?> save(ReviewDto reviewDto);
    ResponseEntity<?> update(ReviewDto reviewDto);
    ResponseEntity<?> delete(ReviewDto reviewDto);

    List<ReviewDto> findAllByHotelAndActive(HotelDto hotelDto);
}
