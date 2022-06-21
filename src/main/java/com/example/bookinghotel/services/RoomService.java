package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.RoomDto;
import com.example.bookinghotel.models.request.ToSaveRoom;
import org.springframework.http.ResponseEntity;

public interface RoomService {

    RoomDto save(RoomDto roomDto);

    ResponseEntity<?> saveRoom(ToSaveRoom saveRoom);
    ResponseEntity<?> update(RoomDto roomDto);
    ResponseEntity<?> delete(RoomDto roomDto);

    RoomDto findById(Long roomId);

}
