package com.example.bookinghotel.controllers;

import com.example.bookinghotel.models.dtos.*;
import com.example.bookinghotel.models.dtos.enumdtos.RoleDto;
import com.example.bookinghotel.models.dtos.enumdtos.StatusOfBookDto;
import com.example.bookinghotel.models.dtos.enumdtos.StatusOfUserDto;
import com.example.bookinghotel.models.dtos.enumdtos.TypeOfRoomDto;
import com.example.bookinghotel.services.*;
import com.example.bookinghotel.services.enumService.RoleService;
import com.example.bookinghotel.services.enumService.StatusOfBookService;
import com.example.bookinghotel.services.enumService.StatusOfUserService;
import com.example.bookinghotel.services.enumService.TypeOfRoomService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminController {

    @Autowired RoleService roleService;
    @Autowired StatusOfBookService statusOfBookService;
    @Autowired StatusOfUserService statusOfUserService;
    @Autowired TypeOfRoomService typeOfRoomService;
    @Autowired BookHistoryService bookHistoryService;
    @Autowired BookingService bookingService;
    @Autowired CityService cityService;
    @Autowired HotelService hotelService;
    @Autowired PriceService priceService;
    @Autowired ReplyToReviewService replyToReviewService;
    @Autowired ReviewService reviewService;
    @Autowired RoomService roomService;
    @Autowired UserService userService;

    @PostMapping("/saveRole")
    public ResponseEntity<?> saveRole(@RequestBody RoleDto roleDto){
        return roleService.save(roleDto);
    }
    @PutMapping("/updateRole")
    public ResponseEntity<?> updateRole(@RequestBody RoleDto roleDto){
        return roleService.update(roleDto);
    }
    @DeleteMapping("/deleteRole")
    public ResponseEntity<?> deleteRole(@RequestBody RoleDto roleDto){
        return roleService.delete(roleDto);
    }
    @PostMapping("/saveStatusOfBook")
    public ResponseEntity<?> saveStatusOfBook(@RequestBody StatusOfBookDto statusOfBookDto){
        return statusOfBookService.save(statusOfBookDto);
    }
    @PutMapping("/updateStatusOfBook")
    public ResponseEntity<?> updateStatusOfBook(@RequestBody StatusOfBookDto statusOfBookDto){
        return statusOfBookService.update(statusOfBookDto);
    }
    @DeleteMapping("/deleteStatusOfBook")
    public ResponseEntity<?> deleteStatusOfBook(@RequestBody StatusOfBookDto statusOfBookDto){
        return statusOfBookService.delete(statusOfBookDto);
    }
    @PostMapping("/saveStatusOfUser")
    public ResponseEntity<?> saveStatusOfUser(@RequestBody StatusOfUserDto statusOfUserDto){
        return statusOfUserService.save(statusOfUserDto);
    }
    @PutMapping("/updateStatusOfUser")
    public ResponseEntity<?> updateStatusOfUser(@RequestBody StatusOfUserDto statusOfUserDto){
        return statusOfUserService.update(statusOfUserDto);
    }
    @DeleteMapping("/deleteStatusOfUser")
    public ResponseEntity<?> deleteStatusOfUser(@RequestBody StatusOfUserDto statusOfUserDto){
        return statusOfUserService.delete(statusOfUserDto);
    }
    @PostMapping("/saveTypeOfRoom")
    public ResponseEntity<?> saveTypeOfRoom(@RequestBody TypeOfRoomDto typeOfRoomDto){
        return typeOfRoomService.save(typeOfRoomDto);
    }
    @PutMapping("/updateTypeOfRoom")
    public ResponseEntity<?> updateTypeOfRoom(@RequestBody TypeOfRoomDto typeOfRoomDto){
        return typeOfRoomService.update(typeOfRoomDto);
    }
    @DeleteMapping("/deleteTypeOfRoom")
    public ResponseEntity<?> deleteTypeOfRoom(@RequestBody TypeOfRoomDto typeOfRoomDto){
        return typeOfRoomService.delete(typeOfRoomDto);
    }
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
    @PutMapping("/updateReview")
    public ResponseEntity<?> updateReview(@RequestBody ReviewDto reviewDto){
        return reviewService.update(reviewDto);
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
