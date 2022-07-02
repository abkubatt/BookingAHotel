package com.example.bookinghotel.models.response;

import com.example.bookinghotel.models.entities.Booking;
import com.example.bookinghotel.models.entities.Photo;
import com.example.bookinghotel.models.enums.EBedType;
import com.example.bookinghotel.models.enums.ETypeOfView;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ToReturnHotelsRoomsBooking {
    String hotelName;
    String hotelDescription;
    String hotelAddress;
    byte hotelStar;
    String hotelPhone;
    double hotelCurrentScore;
    String hotelEmail;
    String hotelCity;
    List<Photo> photos;


    int roomCapacity;
    EBedType bedType;
    float square;
    boolean wifi;
    ETypeOfView typeOfView;
    String roomCategory;

    float price;
    Booking booking;




}
