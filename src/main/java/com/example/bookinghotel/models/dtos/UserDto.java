package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.enums.ERole;
import lombok.Data;


@Data
public class UserDto {
    Long id;
    String name;
    String email;
    ERole role;
    boolean active;
}
