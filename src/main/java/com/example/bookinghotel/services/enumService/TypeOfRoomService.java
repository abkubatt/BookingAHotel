package com.example.bookinghotel.services.enumService;

import com.example.bookinghotel.models.dtos.enumdtos.TypeOfRoomDto;
import org.springframework.http.ResponseEntity;

public interface TypeOfRoomService {

    ResponseEntity<?> save(TypeOfRoomDto typeOfRoomDto);
    ResponseEntity<?> update(TypeOfRoomDto typeOfRoomDto);
    ResponseEntity<?> delete(TypeOfRoomDto typeOfRoomDto);
}
