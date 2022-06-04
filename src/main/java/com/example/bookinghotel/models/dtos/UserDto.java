package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.dtos.enumdtos.RoleDto;
import com.example.bookinghotel.models.dtos.enumdtos.StatusOfUserDto;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    Long id;
    String name;
    String email;
    Set<RoleDto> roles = new HashSet<>();
//    StatusOfUserDto statusOfUser;
    boolean active;
}
