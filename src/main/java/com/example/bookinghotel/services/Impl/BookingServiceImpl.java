package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.BookingDao;
import com.example.bookinghotel.mappers.BookingMapper;
import com.example.bookinghotel.models.dtos.BookingDto;
import com.example.bookinghotel.models.entities.Booking;

import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingDao bookingDao;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;


    @Override
    public ResponseEntity<?> save(BookingDto bookingDto) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        booking.setActive(true);
        Booking bookingSaved = bookingDao.save(booking);
        return new ResponseEntity<>(bookingSaved, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(BookingDto bookingDto) {
        boolean isExists = bookingDao.existsById(bookingDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("User not found"), HttpStatus.NOT_FOUND);
        }else {
            Booking booking = bookingMapper.toEntity(bookingDto);
            Booking updatedBooking = bookingDao.save(booking);
            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> delete(BookingDto bookingDto) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        booking.setActive(false);
        ResponseEntity<?> bookingDeleted = update(bookingMapper.toDto(booking));
        if(bookingDeleted.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(bookingDeleted,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Message.of("Booking not deleted"), HttpStatus.NOT_FOUND);
        }
    }

}
