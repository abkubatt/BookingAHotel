package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.enums.EBedType;
import com.example.bookinghotel.models.enums.ETypeOfView;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomDto {
    Long id;
    int capacity;
    EBedType bedType;
    float square;
    boolean wifi;
    HotelDto hotel;
    ETypeOfView typeOfView;
    boolean active;
    List<PhotoDto> photos;
    PriceDto price;
}
