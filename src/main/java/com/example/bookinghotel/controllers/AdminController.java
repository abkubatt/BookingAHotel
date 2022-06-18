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
    private HotelService hotelService;
    @Autowired UserService userService;

    @Autowired
    CityService cityService;
    @PostMapping("/saveHotel")
    public ResponseEntity<?> saveHotel(@RequestBody HotelDto hotelDto){
        return hotelService.save(hotelDto);
    }
    @PutMapping("updateHotel")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
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

    @PutMapping("/confirmHotel")
    public ResponseEntity<?> confirmHotel(@RequestParam Long hotelId){
        return hotelService.confirm(hotelId);
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

    @PostMapping("/saveCity")
    public ResponseEntity<?> saveCity(@RequestBody CityDto cityDto){
        return cityService.save(cityDto);
    }
    @PutMapping("/updateCity")
    public ResponseEntity<?> updateCity(@RequestBody CityDto cityDto){
        return cityService.update(cityDto);
    }
    @PutMapping("deleteCity")
    public ResponseEntity<?> deleteCity(@RequestBody CityDto cityDto){
        return cityService.delete(cityDto);
    }

}
