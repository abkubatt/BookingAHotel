package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.RoomDto;
import com.example.bookinghotel.models.enums.EBedType;
import com.example.bookinghotel.models.request.ToSaveRoom;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomService {

    RoomDto save(RoomDto roomDto);

    ResponseEntity<?> saveRoom(ToSaveRoom saveRoom);
    ResponseEntity<?> update(RoomDto roomDto);
    ResponseEntity<?> delete(RoomDto roomDto);

    RoomDto findById(Long roomId);
    RoomDto findByHotel(HotelDto hotelDto);
    List<RoomDto> findRoomsByHotel(HotelDto hotelDto, EBedType bedType);


}
