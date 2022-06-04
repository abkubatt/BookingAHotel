package com.example.bookinghotel.models.dtos.enumdtos;

import com.example.bookinghotel.models.enums.ERole;
import lombok.Data;

@Data
public class RoleDto {
    Long id;
    ERole role;
}
