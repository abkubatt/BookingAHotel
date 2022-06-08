package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.enums.EBedType;
import com.example.bookinghotel.models.enums.ETypeOfView;
import lombok.Data;



@Data
public class RoomDto {
    Long id;
    int capacity;
    EBedType bedType;
    float square;
    boolean wifi;
    Hotel hotel;
    ETypeOfView typeOfView;
    boolean active;
}
