package com.example.bookinghotel.models.dtos.enumdtos;

import com.example.bookinghotel.models.enums.EStatusOfUser;
import lombok.Data;

@Data
public class StatusOfUserDto {
    Long id;
    EStatusOfUser statusOfUser;

}
