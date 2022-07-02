package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.enums.EHotelStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelDto {
    @JsonIgnore
    Long id;
    String name;
    String description;
    String address;
    byte star;
    String phone;
    @JsonIgnore
    double currentScore;
    String email;
    CityDto city;
    EHotelStatus hotelStatus;
    UserDto manager;
}
