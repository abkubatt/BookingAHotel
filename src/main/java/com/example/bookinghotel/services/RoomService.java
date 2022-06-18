package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.PriceDto;
import com.example.bookinghotel.models.dtos.RoomDto;
import com.example.bookinghotel.models.request.SaveRoom;
import org.springframework.http.ResponseEntity;

public interface RoomService {

    ResponseEntity<?> save(SaveRoom saveRoom);
    ResponseEntity<?> update(RoomDto roomDto);
    ResponseEntity<?> delete(RoomDto roomDto);
}
