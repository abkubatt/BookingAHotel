package com.example.bookinghotel.controllers;

import com.example.bookinghotel.models.dtos.*;

import com.example.bookinghotel.services.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminController {

    @Autowired
    BookHistoryService bookHistoryService;
    @Autowired
    BookingService bookingService;
    @Autowired
    CityService cityService;
    @Autowired
    HotelService hotelService;
    @Autowired
    PriceService priceService;
    @Autowired ReplyToReviewService replyToReviewService;
    @Autowired ReviewService reviewService;
    @Autowired RoomService roomService;
    @Autowired UserService userService;


    @PostMapping("/saveBooking")
    public ResponseEntity<?> saveBooking(@RequestBody BookingDto bookingDto){
        return bookingService.save(bookingDto);
    }
    @PutMapping("/updateBooking")
    public ResponseEntity<?> updateBooking(@RequestBody BookingDto bookingDto){
        return bookingService.update(bookingDto);
    }
    @DeleteMapping("/deleteBooking")
    public ResponseEntity<?> deleteBooking(@RequestBody BookingDto bookingDto){
        return bookingService.delete(bookingDto);
    }
    @PostMapping("/saveCity")
    public ResponseEntity<?> saveCity(@RequestBody CityDto cityDto){
        return cityService.save(cityDto);
    }
    @PutMapping("/updateCity")
    public ResponseEntity<?> updateCity(@RequestBody CityDto cityDto){
        return cityService.update(cityDto);
    }
    @DeleteMapping("/deleteCity")
    public ResponseEntity<?> deleteCity(@RequestBody CityDto cityDto){
        return cityService.delete(cityDto);
    }
    @PostMapping("/saveHotel")
    public ResponseEntity<?> saveHotel(@RequestBody HotelDto hotelDto){
        return hotelService.save(hotelDto);
    }
    @PutMapping("/updateHotel")
    public ResponseEntity<?> updateHotel(@RequestBody HotelDto hotelDto){
        return hotelService.update(hotelDto);
    }
    @DeleteMapping("/deleteHotel")
    public ResponseEntity<?> deleteHotel(@RequestBody HotelDto hotelDto){
        return hotelService.delete(hotelDto);
    }
    @PostMapping("/savePrice")
    public ResponseEntity<?> savePrice(@RequestBody PriceDto priceDto){
        return priceService.save(priceDto);
    }
    @PutMapping("/updatePrice")
    public ResponseEntity<?> updatePrice(@RequestBody PriceDto priceDto){
        return priceService.update(priceDto);
    }
    @DeleteMapping("/deletePrice")
    public ResponseEntity<?> deletePrice(@RequestBody PriceDto priceDto){
        return priceService.delete(priceDto);
    }
    @PostMapping("/saveReplyToReview")
    public ResponseEntity<?> saveReplyToReview(@RequestBody ReplyToReviewDto replyToReviewDto){
        return replyToReviewService.save(replyToReviewDto);
    }
    @DeleteMapping("/deleteReplyToReview")
    public ResponseEntity<?> deleteReplyToReview(@RequestBody ReplyToReviewDto replyToReviewDto){
        return replyToReviewService.delete(replyToReviewDto);
    }
    @PostMapping("/saveReveiw")
    public ResponseEntity<?> saveReview(@RequestBody ReviewDto reviewDto){
        return reviewService.save(reviewDto);
    }

    @DeleteMapping("/deleteReview")
    public ResponseEntity<?> deleteReview(@RequestBody ReviewDto reviewDto){
        return reviewService.delete(reviewDto);
    }
    @PostMapping("/saveRoom")
    public ResponseEntity<?> saveRoom(@RequestBody RoomDto roomDto){
        return roomService.save(roomDto);
    }
    @PutMapping("/updateRoom")
    public ResponseEntity<?> updateRoom(@RequestBody RoomDto roomDto){
        return roomService.update(roomDto);
    }
    @DeleteMapping("/deleteRoom")
    public ResponseEntity<?> deleteRoom(@RequestBody RoomDto roomDto){
        return roomService.delete(roomDto);
    }
    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto){
        return userService.update(userDto);
    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto){
        return userService.delete(userDto);
    }


}
