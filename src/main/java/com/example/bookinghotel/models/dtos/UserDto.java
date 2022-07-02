package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.enums.ERole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    @JsonIgnore
    Long id;
    String name;
    String email;
    ERole role;
    @JsonIgnore
    boolean active;
}
