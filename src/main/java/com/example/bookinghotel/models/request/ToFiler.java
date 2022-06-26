package com.example.bookinghotel.models.request;

import com.example.bookinghotel.models.enums.EBedType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ToFiler {
    @NotBlank(message = "City id must not be empty")
    Long cityId;
    @NotBlank(message = "CheckInDate id must not be empty")
    String checkInDate;
    @NotBlank(message = "CheckOutDate id must not be empty")
    String checkOutDate;
    @NotBlank(message = "Number of Person id must not be empty")
    Integer numberOfPerson;
    @NotBlank(message = "Number Of Room id must not be empty")
    int numberOfRoom;
    @NotBlank(message = "Bed type must not be empty")
    EBedType bedType;
}
