package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.RoomCategoryDto;
import com.example.bookinghotel.models.entities.RoomCategory;
import com.example.bookinghotel.models.request.ToSaveCategoryAndPrice;
import org.springframework.http.ResponseEntity;

public interface RoomCategoryService {
    RoomCategoryDto save(RoomCategoryDto roomCategoryDto);

    ResponseEntity<?> saveCategoryAndPrice(ToSaveCategoryAndPrice saveCategoryAndPrice);

    ResponseEntity<?> update(RoomCategoryDto roomCategoryDto);

    ResponseEntity<?> delete(RoomCategoryDto roomCategoryDto);
    RoomCategoryDto findById(Long roomCategoryId);
}
