package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.PhotoDto;
import org.springframework.http.ResponseEntity;

public interface PhotoService {
    PhotoDto save(PhotoDto photoDto);
}
