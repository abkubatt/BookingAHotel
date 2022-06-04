package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.BookingDao;
import com.example.bookinghotel.mappers.BookingMapper;
import com.example.bookinghotel.models.dtos.BookingDto;
import com.example.bookinghotel.models.entities.Booking;
import com.example.bookinghotel.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingDao bookingDao;
    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    @Override
    public BookingDto save(BookingDto bookingDto) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        return bookingMapper.toDto(bookingDao.save(booking));
    }
}
