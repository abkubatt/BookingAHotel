package com.example.bookinghotel.models.dtos.enumdtos;

import com.example.bookinghotel.models.enums.EStatusOfBook;
import lombok.Data;

@Data
public class StatusOfBookDto {
    Long id;
    EStatusOfBook statusOfBook;

}
