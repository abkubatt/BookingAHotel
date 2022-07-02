package com.example.bookinghotel.models.request;

import com.example.bookinghotel.models.enums.EBedType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ToFiler {
    @NotBlank(message = "City id must not be empty")
    Long cityId;
    @NotBlank(message = "CheckInDate id must not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate checkInDate;
    @NotBlank(message = "CheckOutDate id must not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate checkOutDate;
    @NotBlank(message = "Number of Person id must not be empty")
    int numberOfPerson;
//    @NotBlank(message = "Number Of Room id must not be empty")
//    int numberOfRoom;
    @NotBlank(message = "Bed type must not be empty")
    EBedType bedType;
}
