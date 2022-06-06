package com.example.bookinghotel.services.enumService;

import com.example.bookinghotel.models.dtos.enumdtos.RoleDto;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<?> save(RoleDto roleDto);
    ResponseEntity<?> update(RoleDto roleDto);
    ResponseEntity<?> delete(RoleDto roleDto);
}
