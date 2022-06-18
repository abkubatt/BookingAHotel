package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.PhotoDto;
import com.example.bookinghotel.models.entities.Photo;
import org.springframework.http.ResponseEntity;

public interface PhotoService {
    PhotoDto save(PhotoDto photoDto);

    ResponseEntity<?> savePhoto(PhotoDto photoDto);

    ResponseEntity<?> updatePhoto(PhotoDto photoDto);

    ResponseEntity<?> deletePhoto(Long photoId);
}
