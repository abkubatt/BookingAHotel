package com.example.bookinghotel.models.response;

import com.example.bookinghotel.models.enums.EBedType;
import com.example.bookinghotel.models.enums.ETypeOfView;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RoomFilterResponse {
    Long id;
    int capacity;
    EBedType bedType;
    float square;
    boolean wifi;
    ETypeOfView typeOfView;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    float totalSum;

}
