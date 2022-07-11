package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.ReviewDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {

    ResponseEntity<?> save(ReviewDto reviewDto);
    ReviewDto saveInProject(ReviewDto reviewDto);
    ResponseEntity<?> update(ReviewDto reviewDto);
    ResponseEntity<?> delete(Long reviewId);

    List<ReviewDto> findAllByHotelAndActive(Long hotelId);

    ReviewDto findById(Long reviewId);
}
