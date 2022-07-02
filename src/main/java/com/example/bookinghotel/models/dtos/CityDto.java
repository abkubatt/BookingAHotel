package com.example.bookinghotel.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityDto {
    @JsonIgnore
    Long id;
    String name;
    @JsonIgnore
    boolean active;
}
