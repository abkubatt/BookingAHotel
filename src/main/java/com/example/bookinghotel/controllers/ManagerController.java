package com.example.bookinghotel.controllers;

import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.services.HotelService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/manager")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerController {
    @Autowired
    HotelService hotelService;
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

}
