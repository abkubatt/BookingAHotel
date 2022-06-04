package com.example.bookinghotel.controllers.enumControllers;

import com.example.bookinghotel.models.dtos.enumdtos.TypeOfRoomDto;
import com.example.bookinghotel.services.enumService.TypeOfRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/typeOfRoom")
public class TypeOfRoomController {
    @Autowired
    private TypeOfRoomService typeOfRoomService;

    @PostMapping("/save")
    public TypeOfRoomDto save(@RequestBody TypeOfRoomDto typeOfRoomDto){
        return typeOfRoomService.save(typeOfRoomDto);
    }
}
