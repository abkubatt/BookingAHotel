package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.enums.EHotelStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelDto {
    Long id;
    String name;
    String description;
    String address;
    byte star;
   // List<PhotoDto> photos;
    String phone;
    double currentScore;
    String email;
    CityDto city;
    EHotelStatus hotelStatus;
    List<UserDto> manager;
}
