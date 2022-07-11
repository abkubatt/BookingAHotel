package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.Exceptions.BookingException;
import com.example.bookinghotel.Exceptions.CancelBookingErrorException;
import com.example.bookinghotel.configuration.EMailSender;
import com.example.bookinghotel.dao.BookingDao;
import com.example.bookinghotel.mappers.BookingMapper;
import com.example.bookinghotel.mappers.HotelMapper;
import com.example.bookinghotel.mappers.RoomMapper;
import com.example.bookinghotel.mappers.UserMapper;
import com.example.bookinghotel.models.dtos.*;
import com.example.bookinghotel.models.entities.Booking;

import com.example.bookinghotel.models.enums.EStatusBooking;
import com.example.bookinghotel.models.request.ToCancelBooking;
import com.example.bookinghotel.models.request.ToSaveBooking;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.models.response.ResponseEmail;
import com.example.bookinghotel.services.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private EMailSender emailSender;
    @Autowired
    private RoomService roomService;
    @Autowired
    private BookHistoryService bookHistoryService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;

    private HotelMapper hotelMapper = HotelMapper.INSTANCE;
    private RoomMapper roomMapper = RoomMapper.INSTANCE;
    private UserMapper userMapper = UserMapper.INSTANCE;


    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;


    @Override
    @Transactional
    public BookingDto save( BookingDto bookingDto) throws BookingException {

        try {
            Booking booking = bookingMapper.toEntity(bookingDto);
            booking.setStatusBooking(EStatusBooking.ACTIVE);

            Booking bookingSaved = bookingDao.save(booking);

            BookHistoryDto bookHistory = new BookHistoryDto();

            bookHistory.setBooking(bookingMapper.toDto(booking));
            bookHistory.setChangeDate(LocalDate.now());
            bookHistory.setComment(booking.getComment());
            bookHistory.setRoom(roomMapper.toDto(booking.getRoom()));
            bookHistory.setCheckInDate(booking.getCheckInDate());
            bookHistory.setCheckOutDate(booking.getCheckOutDate());
            bookHistory.setUser(userMapper.toDto(booking.getGuest()));
            bookHistory.setGuest(userMapper.toDto(booking.getGuest()));
            bookHistory.setStatusBooking(booking.getStatusBooking());

            ResponseEmail responseEmail = new ResponseEmail();
            responseEmail.setPriceOfBooking(booking.getPriceOfBook());
            responseEmail.setStartDate(booking.getCheckInDate().format(DateTimeFormatter.ofPattern("dd-MMM-yy")));
            responseEmail.setEndDate(booking.getCheckOutDate().format(DateTimeFormatter.ofPattern("dd-MMM-yy")));
            responseEmail.setUserName(booking.getGuest().getName());
            responseEmail.setHotelName(booking.getHotel().getName());


            ResponseEntity<?> sendAnEmailToTheUsersEmail = sendCode(booking.getGuest().getEmail(),responseEmail);

            ResponseEntity<?> saveBookHistory = bookHistoryService.save(bookHistory);

            logger.info("Booking saved: -> " + bookingSaved);
            return bookingDto;
        }catch (BookingException e){
            logger.error("Booking not saved: -> " + bookingDto);

            BookingException bookingException = new BookingException("Error while saving booking");
            bookingException.printStackTrace();
            System.out.println(bookingException.getMessage());
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveBooking(ToSaveBooking saveBooking) {
        try {

            HotelDto hotelDto = hotelService.findById(saveBooking.getHotelId());
            RoomDto roomDto = roomService.findById(saveBooking.getRoomId());
            UserDto userDto = userService.findById(saveBooking.getGuestId());

            BookingDto bookingDto = new BookingDto();
            bookingDto.setHotel(hotelDto);
            bookingDto.setRoom(roomDto);
            bookingDto.setCheckInDate(saveBooking.getCheckInDate());
            bookingDto.setCheckOutDate(saveBooking.getCheckOutDate());
            bookingDto.setGuest(userDto);
            bookingDto.setComment(saveBooking.getComment());
            bookingDto.setPriceOfBook(saveBooking.getPriceOfBook());

            BookingDto savedBooking = save(bookingDto);

            logger.info("saveBooking successfully saved: -> " + savedBooking);
            return new ResponseEntity<>(savedBooking, HttpStatus.OK);
        } catch (BookingException b) {
            BookingException bookingException = new BookingException("Error while saving saveBooking booking");
            bookingException.printStackTrace();
            System.out.println(bookingException.getMessage());
            b.printStackTrace();
            System.out.println(b.getMessage());

            logger.error("saveBooking method filed, not saved: -> " + saveBooking);

            return new ResponseEntity<>(b.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
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
    public BookingDto findById(Long id) {
        Booking booking = bookingDao.findById(id).orElse(null);
        //if (booking != null) {
            return bookingMapper.toDto(booking);
        //}
        //return null;
    }


    @Override
    @Transactional
    public ResponseEntity<?> cancelBooking(ToCancelBooking toCancelBooking) {
        try {
            BookingDto booking = findById(toCancelBooking.getBookingId());
            Booking entityBooking = bookingMapper.toEntity(booking);
            entityBooking.setStatusBooking(EStatusBooking.INACTIVE);
            ResponseEntity<?> canceledBooking = update(bookingMapper.toDto(entityBooking));
            UserDto userDto = userService.findById(toCancelBooking.getUserId());


            BookHistoryDto bookHistory = new BookHistoryDto();
            bookHistory.setBooking(booking);
            bookHistory.setChangeDate(LocalDate.now());
            bookHistory.setComment(toCancelBooking.getComment());
            bookHistory.setRoom(roomMapper.toDto(entityBooking.getRoom()));
            bookHistory.setCheckInDate(entityBooking.getCheckInDate());
            bookHistory.setCheckOutDate(entityBooking.getCheckOutDate());
            bookHistory.setUser(userDto);
            bookHistory.setGuest(userMapper.toDto(entityBooking.getGuest()));
            bookHistory.setStatusBooking(entityBooking.getStatusBooking());

            ResponseEmail responseEmail = new ResponseEmail();
            responseEmail.setPriceOfBooking(booking.getPriceOfBook());
            responseEmail.setStartDate(booking.getCheckInDate().format(DateTimeFormatter.ofPattern("dd-MMM-yy")));
            responseEmail.setEndDate(booking.getCheckOutDate().format(DateTimeFormatter.ofPattern("dd-MMM-yy")));
            responseEmail.setUserName(booking.getGuest().getName());
            responseEmail.setHotelName(booking.getHotel().getName());


            ResponseEntity<?> savedBookingHistory = bookHistoryService.save(bookHistory);
            ResponseEntity<?> sendAnEmailToTheUsersEmail = sendCode2(entityBooking.getGuest().getEmail(),responseEmail);
           // if (canceledBooking.getStatusCode().equals(HttpStatus.OK) && savedBookingHistory.getStatusCode().equals(HttpStatus.OK) && savedBookingHistory.getStatusCode().equals(HttpStatus.OK)) {
            logger.info("Booking successfully canceled: -> " + savedBookingHistory);
            return new ResponseEntity<>(canceledBooking, HttpStatus.OK);
            //}
        } catch (CancelBookingErrorException c) {

            logger.error("cancel Booking filed: -> " + toCancelBooking.getBookingId()  + " userId " + toCancelBooking.getUserId());
            CancelBookingErrorException cancelBooking = new CancelBookingErrorException("Error while cancelling booking");
            return new ResponseEntity<>(cancelBooking.getMessage(), HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity<>(Message.of("Success"), HttpStatus.OK);
    }



    @Override
    public ResponseEntity<?> sendCode(String email,ResponseEmail responseEmail) {
        try {
            emailSender.sendSimpleMessage(email, "You have successfully booked a hotel",
                    "Hotel: " +  responseEmail.getHotelName() +
                         "     Guest name: " + responseEmail.getUserName() +
                          "     Start Date: " + responseEmail.getStartDate() +
                          "     End Date: " + responseEmail.getEndDate() +
                          "     Price of booking cost: " + responseEmail.getPriceOfBooking());
            logger.info("Message successfully sent to: -> " + email);
            return new ResponseEntity<>(Message.of("Success"), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Message filed while sending message to: ->" + email);
            return new ResponseEntity<>(Message.of("Error while sending code to email"), HttpStatus.NOT_IMPLEMENTED);
        }
    }
    @Override
    public ResponseEntity<?> sendCode2(String email,ResponseEmail responseEmail) {
        try {
            emailSender.sendSimpleMessage(email, "You have successfully canceled your hotel reservation",
                    "Hotel: " +  responseEmail.getHotelName() +
                            "     Guest name: " + responseEmail.getUserName() +
                            "     Start Date: " + responseEmail.getStartDate() +
                            "     End Date: " + responseEmail.getEndDate() +
                            "     Price of booking cost: " + responseEmail.getPriceOfBooking());
            logger.info("Message successfully sent to: -> " + email);

            return new ResponseEntity<>(Message.of("Success"), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Message filed while sending message to: ->" + email);

            return new ResponseEntity<>(Message.of("Error while sending code to email"), HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @Override
    public List<BookingDto> findAllByHotel(Long hotelId) {
        List<Booking> booking = bookingDao.findAllByHotel(hotelId);
        System.out.println(booking.get(0).getCheckOutDate());
        System.out.println(booking.get(0).getStatusBooking());
        if (booking == null) logger.error("Bookings not found from database with this hotelId" + hotelId);
        logger.info("Bookings successfully found from database: -> " + booking);
        return bookingMapper.toDtoList(booking);
    }


    @Override
    public List<BookingDto> findAllBooking(Long hotelId,int numberOfPerson,LocalDate checkInDate, LocalDate checkOutDate) {
        List<Booking> booking = bookingDao.findAllBooking(hotelId,numberOfPerson,checkInDate, checkOutDate);
        if (booking == null) logger.error("Booking not found from database with this information: -> " + numberOfPerson + " "+ checkInDate +  " "+ " " +checkOutDate);
        logger.info("Booking successfully found from database : -> " + booking);
        return bookingMapper.toDtoList(booking);
    }

    @Override
    public List<BookingDto> findAllByRoomAndActive(RoomDto roomDto, EStatusBooking statusBooking) {
        List<Booking> bookings = bookingDao.findAllByRoomAndStatusBooking(roomMapper.toEntity(roomDto), statusBooking);
        return bookingMapper.toDtoList(bookings);
    }
}
