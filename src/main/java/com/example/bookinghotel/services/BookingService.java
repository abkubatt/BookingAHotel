package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.BookingDto;

public interface BookingService {

    BookingDto save(BookingDto bookingDto);
}
