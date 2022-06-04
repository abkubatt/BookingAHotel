package com.example.bookinghotel.controllers.enumControllers;

import com.example.bookinghotel.models.dtos.enumdtos.StatusOfBookDto;
import com.example.bookinghotel.services.enumService.StatusOfBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/statusOfBook")
public class StatusOfBookController {
    @Autowired
    private StatusOfBookService statusOfBookService;

    @PostMapping("/save")
    public StatusOfBookDto save(@RequestBody StatusOfBookDto statusOfBookDto){
        return statusOfBookService.save(statusOfBookDto);
    }
}
