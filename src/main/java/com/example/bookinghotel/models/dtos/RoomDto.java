package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.dtos.enumdtos.TypeOfRoomDto;
import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.enumentities.TypeOfRoom;
import lombok.Data;


@Data
public class RoomDto {
    Long id;
    int capacity;
    String bedType;
    float square;
    boolean wifi;
    String view;
    HotelDto hotel;
    TypeOfRoomDto typeOfRoom;
    boolean active;
}
