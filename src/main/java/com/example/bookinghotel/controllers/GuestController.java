package com.example.bookinghotel.controllers;

import com.example.bookinghotel.models.dtos.BookingDto;
import com.example.bookinghotel.models.dtos.ReviewDto;
import com.example.bookinghotel.models.enums.EBedType;
import com.example.bookinghotel.models.request.ToFiler;
import com.example.bookinghotel.services.BookingService;
import com.example.bookinghotel.services.HotelService;
import com.example.bookinghotel.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/guest")
public class GuestController {
    @Autowired
    BookingService bookingService;
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private HotelService hotelService;

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


    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestParam Long cityId ,
                                    @RequestParam
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate checkInDate,
                                    @RequestParam
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
                                    @RequestParam int guestAmount,
                                    @RequestParam EBedType bedType){
        return hotelService.filter2(cityId,checkInDate,checkOutDate,bedType);
    }
}
