package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.RoomDto;
import org.springframework.http.ResponseEntity;

public interface RoomService {

    ResponseEntity<?> save(RoomDto roomDto);
    ResponseEntity<?> update(RoomDto roomDto);
    ResponseEntity<?> delete(RoomDto roomDto);
}
