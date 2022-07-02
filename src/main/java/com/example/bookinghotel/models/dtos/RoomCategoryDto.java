package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.enums.ETypeOfRoom;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomCategoryDto {
    @JsonIgnore
    Long id;
    ETypeOfRoom typeOfRoom;
}
