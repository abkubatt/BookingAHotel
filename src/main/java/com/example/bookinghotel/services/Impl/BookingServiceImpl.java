package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.Exceptions.BookingException;
import com.example.bookinghotel.Exceptions.CancelBookingErrorException;
import com.example.bookinghotel.configuration.EMailSender;
import com.example.bookinghotel.dao.BookingDao;
import com.example.bookinghotel.mappers.BookingMapper;
import com.example.bookinghotel.mappers.RoomMapper;
import com.example.bookinghotel.mappers.UserMapper;
import com.example.bookinghotel.models.dtos.BookHistoryDto;
import com.example.bookinghotel.models.dtos.BookingDto;
import com.example.bookinghotel.models.dtos.UserDto;
import com.example.bookinghotel.models.entities.Booking;

import com.example.bookinghotel.models.enums.EStatusBooking;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.BookHistoryService;
import com.example.bookinghotel.services.BookingService;

import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private EMailSender emailSender;
    @Autowired
    private BookHistoryService bookHistoryService;
    private RoomMapper roomMapper = RoomMapper.INSTANCE;
    private UserMapper userMapper = UserMapper.INSTANCE;
    @Autowired
    private UserService userService;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;


    @Override
    @Transactional
    public ResponseEntity<?> save(BookingDto bookingDto) throws BookingException {
        try {
            Booking booking = bookingMapper.toEntity(bookingDto);
            booking.setStatusBooking(EStatusBooking.ACTIVE);


            BookHistoryDto bookHistory = new BookHistoryDto();

            bookHistory.setBooking(bookingMapper.toDto(booking));
            bookHistory.setChangeDate(LocalDate.now());
            bookHistory.setComment(booking.getComment());
            bookHistory.setRoom(roomMapper.toDto(booking.getRoom()));
            bookHistory.setCheckInDate(bookHistory.getCheckInDate());
            bookHistory.setCheckOutDate(booking.getCheckOutDate());
            bookHistory.setUser(userMapper.toDto(booking.getGuest()));
            bookHistory.setGuest(userMapper.toDto(booking.getGuest()));
            bookHistory.setStatusBooking(booking.getStatusBooking());

            ResponseEntity<?> saveBookHistory = bookHistoryService.save(bookHistory);
            Booking bookingSaved = bookingDao.save(booking);
              //ResponseEntity<?> sendAnEmailToTheUsersEmail = sendCode(booking.getGuest().getEmail());
          //  if (sendAnEmailToTheUsersEmail.getStatusCode().equals(HttpStatus.OK)){

         //   if (bookHistory.getStatusBooking().equals(HttpStatus.OK && saveBookHistory.getStatusCode().equals(HttpStatus.OK && sendAnEmailToTheUsersEmail.getStatusCode().equals(HttpStatus.OK))))
                return new ResponseEntity<>(bookingSaved, HttpStatus.OK);
         //   }
        }catch (BookingException b){
            BookingException bookingException = new BookingException("Error while booking");
            return new ResponseEntity<>(bookingException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        //return new ResponseEntity<>(Message.of("Success"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(BookingDto bookingDto) {
        boolean isExists = bookingDao.existsById(bookingDto.getId());
        if (!isExists) {
            return new ResponseEntity<>(Message.of("User not found"), HttpStatus.NOT_FOUND);
        } else {
            Booking booking = bookingMapper.toEntity(bookingDto);
            Booking updatedBooking = bookingDao.save(booking);
            return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> delete(BookingDto bookingDto) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        booking.setStatusBooking(EStatusBooking.INACTIVE);
        ResponseEntity<?> bookingDeleted = update(bookingMapper.toDto(booking));
        if (bookingDeleted.getStatusCode().equals(HttpStatus.OK)) {
            return new ResponseEntity<>(bookingDeleted, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Message.of("Booking not deleted"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public BookingDto findByIdSecond(Long id) {
        Booking booking = bookingDao.findById(id).orElse(null);
        if (booking != null) {
            return bookingMapper.toDto(booking);
        }
        return null;
    }


    @Override
    @Transactional
    public ResponseEntity<?> cancelBooking(Long bookingId, String comment, Long userId) {
        try {
            BookingDto bookingDto2 = findByIdSecond(bookingId);
            Booking entityBooking = bookingMapper.toEntity(bookingDto2);
            entityBooking.setStatusBooking(EStatusBooking.INACTIVE);
            UserDto userDto = userService.findById(userId);


            BookHistoryDto bookHistory = new BookHistoryDto();
            bookHistory.setBooking(bookingDto2);
            bookHistory.setChangeDate(LocalDate.now());
            bookHistory.setComment(comment);
            bookHistory.setRoom(roomMapper.toDto(entityBooking.getRoom()));
            bookHistory.setCheckInDate(entityBooking.getCheckInDate());
            bookHistory.setCheckOutDate(entityBooking.getCheckOutDate());
            bookHistory.setUser(userDto);
            bookHistory.setGuest(userMapper.toDto(entityBooking.getGuest()));
            bookHistory.setStatusBooking(entityBooking.getStatusBooking());


            ResponseEntity<?> savedBookingHistory = bookHistoryService.save(bookHistory);
            ResponseEntity<?> canceledBooking = update(bookingMapper.toDto(entityBooking));
           // ResponseEntity<?> sendAnEmailToTheUsersEmail = sendCode2(entityBooking.getGuest().getEmail());
            if (canceledBooking.getStatusCode().equals(HttpStatus.OK) && savedBookingHistory.getStatusCode().equals(HttpStatus.OK) && savedBookingHistory.getStatusCode().equals(HttpStatus.OK)) {
                return new ResponseEntity<>(canceledBooking, HttpStatus.OK);
            }
        } catch (CancelBookingErrorException c) {
            CancelBookingErrorException cancelBooking = new CancelBookingErrorException("Error while cancelling booking");
            return new ResponseEntity<>(cancelBooking.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Message.of("Success"), HttpStatus.OK);
    }



    @Override
    public ResponseEntity<?> sendCode(String email) {
        try {
            emailSender.sendSimpleMessage(email, "You have successfully booked a hotel", ".");
            return new ResponseEntity<>(Message.of("Success"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Message.of("Error while sending code to email"), HttpStatus.NOT_IMPLEMENTED);
        }
    }
    @Override
    public ResponseEntity<?> sendCode2(String email) {
        try {
            emailSender.sendSimpleMessage(email, "You have successfully canceled your hotel reservation", ".");
            return new ResponseEntity<>(Message.of("Success"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Message.of("Error while sending code to email"), HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
