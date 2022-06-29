package com.example.bookinghotel.models.request;

import com.example.bookinghotel.models.entities.RoomCategory;
import com.example.bookinghotel.models.enums.ETypeOfRoom;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ToSaveCategoryAndPrice {
    @NotBlank(message = "Type of room id must not be empty")
    ETypeOfRoom typeOfRoom;
    @NotBlank(message = "Price id must not be empty")
    float price;
    @NotBlank(message = "Start date id must not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;
    @NotBlank(message = "End date id must not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
}
