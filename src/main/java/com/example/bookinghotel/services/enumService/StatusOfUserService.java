package com.example.bookinghotel.services.enumService;

import com.example.bookinghotel.models.dtos.enumdtos.StatusOfUserDto;
import org.springframework.http.ResponseEntity;

public interface StatusOfUserService {

    ResponseEntity<?> save(StatusOfUserDto statusOfUserDto);
}
