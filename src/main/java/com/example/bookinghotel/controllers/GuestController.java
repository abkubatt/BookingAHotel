package com.example.bookinghotel.controllers;

import com.example.bookinghotel.models.dtos.BookingDto;
import com.example.bookinghotel.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/guest")
public class GuestController {
    @Autowired
    BookingService bookingService;

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

}
