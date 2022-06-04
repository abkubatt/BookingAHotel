package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.BookHistoryDto;
import org.springframework.http.ResponseEntity;

public interface BookHistoryService {

    ResponseEntity<?> save(BookHistoryDto bookHistoryDto);
}
