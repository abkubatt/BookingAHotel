package com.example.bookinghotel.models.request;

import com.example.bookinghotel.dao.PhotoDao;
import com.example.bookinghotel.models.dtos.PhotoDto;
import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.Photo;
import com.example.bookinghotel.models.entities.Price;
import com.example.bookinghotel.models.enums.EBedType;
import com.example.bookinghotel.models.enums.ETypeOfView;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveRoom {
    @NotBlank(message = "Capacity must not be empty")
    int capacity;
    @NotBlank(message = "Bed type must not be empty")
    EBedType bedType;
    @NotBlank(message = "Square must not be empty")
    float square;
    @NotBlank(message = "Wi-Fi must not be empty")
    boolean wifi;
    @NotBlank(message = "Hotel must not be empty")
    Long hotelId;
    @NotBlank(message = "Type of view must not be empty")
    ETypeOfView typeOfView;
    @NotBlank(message = "Photo must not be empty")
    List<PhotoDto> photos;
    @NotBlank(message = "Price must not be empty")
    float price;
    @NotBlank(message = "Start date must not be empty")
    LocalDate startDate;
    @NotBlank(message = "End date must not be empty")
    LocalDate endDate;

}
