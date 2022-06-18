package com.example.bookinghotel.controllers;

import com.example.bookinghotel.models.dtos.BookingDto;
import com.example.bookinghotel.models.dtos.ReviewDto;
import com.example.bookinghotel.services.BookingService;
import com.example.bookinghotel.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/guest")
public class GuestController {
    @Autowired
    BookingService bookingService;
    @Autowired
    private ReviewService reviewService;


    @PostMapping("/saveReview")
    public ResponseEntity<?> saveReview(@RequestBody ReviewDto reviewDto){
        return reviewService.save(reviewDto);
    }

    @PutMapping("/updateReview")
    public ResponseEntity<?> updateReview(@RequestBody ReviewDto reviewDto){
        return reviewService.update(reviewDto);
    }
    @PutMapping("/deleteReview")
    public ResponseEntity<?> deleteReview(@RequestBody ReviewDto reviewDto){
        return reviewService.delete(reviewDto);
    }

}
