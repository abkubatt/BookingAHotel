package com.example.bookinghotel.controllers.enumControllers;

import com.example.bookinghotel.models.dtos.enumdtos.StatusOfBookDto;
import com.example.bookinghotel.models.dtos.enumdtos.StatusOfUserDto;
import com.example.bookinghotel.services.enumService.StatusOfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/statusOfUser")
public class StatusOfUserController {
    @Autowired
    public StatusOfUserService statusOfUserService;

    @PostMapping("/save")
    public StatusOfUserDto save(@RequestBody StatusOfUserDto statusOfBookDto){
        return statusOfUserService.save(statusOfBookDto);
    }
}
