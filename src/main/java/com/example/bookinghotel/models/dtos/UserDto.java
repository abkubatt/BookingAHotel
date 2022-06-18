package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.enums.ERole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String name;
    String email;
    ERole role;
    boolean active;
}
