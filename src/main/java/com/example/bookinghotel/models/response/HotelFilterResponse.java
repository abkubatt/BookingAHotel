package com.example.bookinghotel.models.response;

import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.RoomDto;
import com.example.bookinghotel.models.entities.Room;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelFilterResponse {
    Long Id;
    String name;
    String description;
    String address;
    String phone;
    Double currentScore;
    String email;
    List<RoomFilterResponse> availableRooms;


}
