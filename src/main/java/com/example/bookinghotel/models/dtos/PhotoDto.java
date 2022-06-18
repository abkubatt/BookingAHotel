package com.example.bookinghotel.models.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhotoDto {
    Long id;
    String link;
    int position;
}
