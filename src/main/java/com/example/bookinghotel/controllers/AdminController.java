package com.example.bookinghotel.controllers;

import com.example.bookinghotel.models.dtos.*;

import com.example.bookinghotel.services.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminController {

    @Autowired
    private HotelService hotelService;
    @Autowired UserService userService;

    @PostMapping("/saveHotel")
    public ResponseEntity<?> saveHotel(@RequestBody HotelDto hotelDto){
        return hotelService.save(hotelDto);
    }
    @PutMapping("updateHotel")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> updateHotel(@RequestBody HotelDto hotelDto){
        return hotelService.update(hotelDto);
    }
    @DeleteMapping("/deleteHotel")
    public ResponseEntity<?> deleteHotel(@RequestParam Long hotelId){
        return hotelService.delete(hotelId);
    }

    @PutMapping("/blockHotel")
    public ResponseEntity<?> blockHotel(@RequestParam Long hotelId){
        return hotelService.blockHotel(hotelId);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto){
        return userService.update(userDto);
    }
    @PutMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto){
        return userService.delete(userDto);
    }

}
