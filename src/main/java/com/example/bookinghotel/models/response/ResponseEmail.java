package com.example.bookinghotel.models.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseEmail {
    String hotelName;
    String userName;
    String startDate;
    String endDate;
    float priceOfBooking;
}
