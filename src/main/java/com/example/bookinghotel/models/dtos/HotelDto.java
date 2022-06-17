package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.City;
import com.example.bookinghotel.models.enums.EHotelStatus;
import lombok.Data;

import java.util.List;


@Data
public class HotelDto {
    Long id;
    String name;
    String description;
    String address;
    byte star;
    List<PhotoDto> photos;
    String phone;
    double currentScore;
    String email;
    CityDto city;
    EHotelStatus hotelStatus;
}
