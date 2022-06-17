package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> save(UserDto userDto);
    ResponseEntity<?> update(UserDto userDto);
    ResponseEntity<?> delete(UserDto userDto);

    UserDto findById(Long userId);
}
