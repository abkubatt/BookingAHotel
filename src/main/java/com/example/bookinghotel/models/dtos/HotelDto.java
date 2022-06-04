package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.City;
import lombok.Data;

@Data
public class HotelDto {
    Long id;
    String name;
    String description;
    String address;
    byte star;
    String photos;
    String phone;
    String email;
    CityDto city;
    boolean active;
}
