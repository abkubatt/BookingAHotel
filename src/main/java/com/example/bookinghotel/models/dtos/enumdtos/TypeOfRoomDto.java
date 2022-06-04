package com.example.bookinghotel.models.dtos.enumdtos;

import com.example.bookinghotel.models.enums.ETypeOfRoom;
import lombok.Data;

@Data
public class TypeOfRoomDto {
    Long id;
    ETypeOfRoom typeOfRoom;

}
