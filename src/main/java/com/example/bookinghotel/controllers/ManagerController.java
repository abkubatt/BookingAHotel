package com.example.bookinghotel.controllers;

import com.example.bookinghotel.models.dtos.*;
import com.example.bookinghotel.models.entities.Price;
import com.example.bookinghotel.models.entities.Room;
import com.example.bookinghotel.services.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/manager")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerController {
    @Autowired HotelService hotelService;
    @Autowired BookingService bookingService;

    @Autowired
    ReplyToReviewService replyToReviewService;
    @Autowired
    RoomService roomService;
    @Autowired
    PriceService priceService;

    @PostMapping("saveBooking")
    //@PreAuthorize("hasRole('MANAGER') or hasRole('GUEST')")
    public ResponseEntity<?> saveBooking(@RequestBody BookingDto bookingDto){
        return bookingService.save(bookingDto);
    }
    @PutMapping("cancelBooking")
   // @PreAuthorize("hasRole('MANAGER') or hasRole('GUEST')")
    public ResponseEntity<?> cancelBooking(@RequestParam Long bookingId,@RequestParam String comment,@RequestParam Long userId){
        return bookingService.cancelBooking(bookingId,comment,userId);
    }
    @PostMapping("/saveReplyToReview")
    public ResponseEntity<?> saveReplyToReview(@RequestBody ReplyToReviewDto replyToReviewDto){
        return replyToReviewService.save(replyToReviewDto);
    }
    @PostMapping("/saveRoom")
    public ResponseEntity<?> saveRoom(@RequestBody RoomDto roomDto){
        return roomService.save(roomDto);
    }

    @PutMapping("/updateRoom")
    public ResponseEntity<?> updateRoom(@RequestBody RoomDto roomDto){
        return roomService.update(roomDto);
    }
    @PutMapping("/deleteRoom")
    public ResponseEntity<?> deleteRoom(@RequestBody RoomDto roomDto){
        return roomService.delete(roomDto);
    }

    @PostMapping("/savePrice")
    public ResponseEntity<?> savePrice(@RequestBody PriceDto priceDto){
        return priceService.save(priceDto);
    }
    @PutMapping("/updatePrice")
    public ResponseEntity<?> updatePrice(@RequestBody PriceDto priceDto){
        return priceService.update(priceDto);
    }
    @PutMapping("/deletePrice")
    public ResponseEntity<?> deletePrice(@RequestBody PriceDto priceDto){
        return priceService.delete(priceDto);
    }



}
