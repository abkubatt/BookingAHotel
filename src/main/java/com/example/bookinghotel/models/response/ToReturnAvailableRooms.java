package com.example.bookinghotel.models.response;

import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.RoomDto;
import com.example.bookinghotel.models.entities.Booking;
import com.example.bookinghotel.models.entities.Photo;
import com.example.bookinghotel.models.enums.EBedType;
import com.example.bookinghotel.models.enums.ETypeOfView;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ToReturnAvailableRooms {
    List<HotelDto> hotels;
    List<Photo> photos;

    List<RoomDto> rooms;

    float price;
    Booking booking;




}
