package com.example.bookinghotel.services.enumService;

import com.example.bookinghotel.models.dtos.enumdtos.StatusOfBookDto;
import org.springframework.http.ResponseEntity;

public interface StatusOfBookService {

    ResponseEntity<?> save(StatusOfBookDto statusOfBookDto);
}
