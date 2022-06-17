package com.example.bookinghotel.controllers;

import com.example.bookinghotel.models.dtos.BookingDto;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.ReplyToReviewDto;
import com.example.bookinghotel.services.BookingService;
import com.example.bookinghotel.services.HotelService;
import com.example.bookinghotel.services.ReplyToReviewService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/manager")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerController {
    @Autowired HotelService hotelService;
    @Autowired BookingService bookingService;

    @Autowired
    ReplyToReviewService replyToReviewService;
    @PutMapping("/updateHotel")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateHotel(@RequestBody HotelDto hotelDto){
        return hotelService.update(hotelDto);
    }
    @PostMapping("saveBooking")
    @PreAuthorize("hasRole('MANAGER') or hasRole('GUEST')")
    public ResponseEntity<?> saveBooking(@RequestBody BookingDto bookingDto){
        return bookingService.save(bookingDto);
    }
    @PutMapping("cancelBooking")
    @PreAuthorize("hasRole('MANAGER') or hasRole('GUEST')")
    public ResponseEntity<?> cancelBooking(@RequestParam Long bookingId,@RequestParam String comment,@RequestParam Long userId){
        return bookingService.cancelBooking(bookingId,comment,userId);
    }
    @PostMapping("/saveReplyToReview")
    public ResponseEntity<?> saveReplyToReview(@RequestBody ReplyToReviewDto replyToReviewDto){
        return replyToReviewService.save(replyToReviewDto);
    }

}
