package com.example.bookinghotel.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ResponseEntity<?> uploadImageToHotel(MultipartFile file, Long hotelId, int orderNum);
}
