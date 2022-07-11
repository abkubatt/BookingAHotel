package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> save(UserDto userDto);
    UserDto saveInProject(UserDto userDto);
    ResponseEntity<?> update(UserDto userDto);
    ResponseEntity<?> delete(Long userId);

    UserDto findById(Long userId);
}
