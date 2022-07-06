package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.BookingDao;
import com.example.bookinghotel.dao.HotelDao;
import com.example.bookinghotel.mappers.CityMapper;
import com.example.bookinghotel.mappers.HotelMapper;
import com.example.bookinghotel.models.dtos.*;
import com.example.bookinghotel.models.entities.*;
import com.example.bookinghotel.models.enums.EBedType;
import com.example.bookinghotel.models.enums.EHotelStatus;
import com.example.bookinghotel.models.enums.EStatusBooking;
import com.example.bookinghotel.models.request.ToFiler;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.*;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class HotelServiceImpl implements HotelService {
    Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);
    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private CityService cityService;
    private final CityMapper cityMapper = CityMapper.INSTANCE;
    private final HotelMapper hotelMapper = HotelMapper.INSTANCE;
    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;


    @Override
    @Transactional
    public ResponseEntity<?> save(HotelDto hotelDto) {
        ResponseEntity<?> savedUser = userService.save(hotelDto.getManager());
        hotelDto.setManager((UserDto) savedUser.getBody());
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        hotel.setHotelStatus(EHotelStatus.NOT_AVAILABLE);
        Hotel saveHotel = hotelDao.save(hotel);
        if (saveHotel == null) logger.error("Failed while saving hotel: -> " + hotelDto);

        logger.info("Hotel successfully saved: -> " + saveHotel);
        return new ResponseEntity<>(saveHotel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(HotelDto hotelDto) {
        boolean isExists = hotelDao.existsById(hotelDto.getId());
        if (!isExists) {
            logger.error("Hotel not found from database -> " + hotelDto);
            return new ResponseEntity<>(Message.of("Hotel not found"), HttpStatus.NOT_FOUND);
        } else {
            Hotel hotel = hotelMapper.toEntity(hotelDto);
            Hotel updatedHotel = hotelDao.save(hotel);
            if (updatedHotel == null) logger.error("Failed while updating hotel: -> " + hotel);

            logger.info("Hotel successfully updated: -> " + updatedHotel);
            return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
        }
    }

    @Override
    public HotelDto findById(Long hotelId) {
        Hotel hotel = hotelDao.findById(hotelId).orElse(null);
        if (hotel == null) logger.error("Hotel not found from database: -> " + hotelId);
        logger.info("Hotel successfully found: -> " + hotel);
        return hotelMapper.toDto(hotel);
    }

    @Override
    public ResponseEntity<?> delete(Long hotelId) {
        HotelDto hotelDto = findById(hotelId);
        if (hotelDto == null) {
            logger.error("Hotel not found from database: -> " + hotelId);
            return new ResponseEntity<>(Message.of("Hotel not found"), HttpStatus.NOT_FOUND);
        }
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        hotel.setHotelStatus(EHotelStatus.INACTIVE);
        ResponseEntity<?> deletedHotel = update(hotelMapper.toDto(hotel));
        if (deletedHotel.getStatusCode().equals(HttpStatus.OK)) {
            logger.info("Hotel successfully deleted: -> " + deletedHotel);
            return new ResponseEntity<>(deletedHotel, HttpStatus.OK);
        } else {
            logger.error("Failed while deleting hotel: -> " + hotelId);
            return new ResponseEntity<>(Message.of("Hotel not deleted"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> blockHotel(Long hotelId) {
        HotelDto hotelDto = findById(hotelId);
        if (hotelDto == null) {
            logger.error("Hotel not found from database: -> " + hotelId);
            return new ResponseEntity<>(Message.of("Hotel not found"), HttpStatus.NOT_FOUND);
        }
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        hotel.setHotelStatus(EHotelStatus.BLOCK);
        ResponseEntity<?> blockHotel = update(hotelMapper.toDto(hotel));
        if (blockHotel.getStatusCode().equals(HttpStatus.OK)) {
            logger.info("Hotel successfully blocked: -> " + blockHotel);
            return new ResponseEntity<>(blockHotel, HttpStatus.OK);
        } else {
            logger.error("Failed while blocking hotel: -> " + hotel);
            return new ResponseEntity<>(Message.of("Hotel not blocked"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> confirm(Long hotelId) {
        HotelDto hotelDto = findById(hotelId);
        if (hotelDto == null) {
            logger.error("Hotel not found from database: -> " + hotelId);
            return new ResponseEntity<>(Message.of("Hotel not found"), HttpStatus.NOT_FOUND);
        }
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        hotel.setHotelStatus(EHotelStatus.ACTIVE);
        ResponseEntity<?> blockHotel = update(hotelMapper.toDto(hotel));
        if (blockHotel.getStatusCode().equals(HttpStatus.OK)) {
            logger.info("Hotel successfully confirmed: -> " + blockHotel);
            return new ResponseEntity<>(blockHotel, HttpStatus.OK);
        } else {
            logger.error("Hotel not confirmed: -> " + hotel);
            return new ResponseEntity<>(Message.of("Hotel not confirmed"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> findAllHotelsByRating(HotelDto hotelDto) {
        return null;
    }


    @Override
    public List<HotelDto> findAllHotelsByCity(Long cityId) {
        CityDto cityDto = cityService.findById(cityId);
        if (cityDto != null) {
            List<Hotel> hotels = hotelDao.findAllByCity(cityDto.getId());
            if (!hotels.isEmpty()) {
                return hotelMapper.toDtoList(hotels);
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    @Override
    public void countCurrentScore() {
        List<HotelDto> hotelDtos = findAll();
        hotelDtos.stream().forEach(x -> {
            List<ReviewDto> reviews = reviewService.findAllByHotelAndActive(x);
            double sum = reviews.stream().mapToDouble(ReviewDto::getScore).sum();
            double currentScore = Math.round((sum / reviews.size()) / 10.0) * 10;
            x.setCurrentScore(currentScore);
            update(x);
        });
    }

    @Override
    public List<HotelDto> findAll() {
        List<Hotel> hotels = hotelDao.findAllHotel();
        return hotelMapper.toDtoList(hotels);
    }

    //    HotelDto hotel = hotelService.findById(hotels.get(0).getId());
//    Hotel checkingHotel = new Hotel();
//    checkingHotel = hotelMapper.toEntity(hotel);
//
//    BookingDto bookingDto = bookingService.findByHotel(hotelMapper.toDto(checkingHotel));
//    RoomDto roomDto = roomService.findByHotel(hotelMapper.toDto(checkingHotel));
//        if (checkingHotel.getCity().equals(filer.getCityId()) && bookingDto.getCheckInDate().equals(filer.getCheckInDate()) && bookingDto.getCheckOutDate().equals(filer.getCheckOutDate()) && roomDto.getCapacity() == filer.getNumberOfPerson() && roomDto.getBedType().equals(filer.getBedType())){
//            logger.info("Hotel(room) is busy: -> " + filer);
//            return new ResponseEntity<>(Message.of("This hotel's room is booked by someone"), HttpStatus.NOT_FOUND);
    @Override
    @Transactional
    public ResponseEntity<?> filter(ToFiler filer) {

        CityDto cityDto = cityService.findById(filer.getCityId());
        List<Hotel> hotels = hotelDao.findAll(filer.getCityId(), filer.getNumberOfPerson(), filer.getBedType());
        List<RoomDto> responseHotel = new ArrayList<>();
        //List<Hotel> responseHotel = new ArrayList<>();

        hotels.stream().forEach(x -> {
            RoomDto rooms = roomService.findByHotel(hotelMapper.toDto(x));
            //RoomDto room = roomService.findByHotel(hotelMapper.toDto(x));
            List<BookingDto> bookingDto = bookingService.findAllByHotel(x.getId());
            System.out.println(bookingDto.size());
            //List<BookingDto> bookings = bookingService.findAllBooking(x.getId(), filer.getNumberOfPerson(),filer.getCheckInDate(), filer.getCheckOutDate());
            //System.out.println(bookings.size());


            bookingDto.stream().forEach(y -> {
                System.out.println(y.getCheckInDate() + " " + y.getCheckOutDate());
//                int ok = y.getCheckInDate().compareTo(filer.getCheckInDate());
//                System.out.println(ok);
//                int ok2 = y.getCheckOutDate().compareTo(filer.getCheckOutDate());
//                System.out.println(ok2);


                if (y.getCheckInDate().equals(filer.getCheckInDate()) || y.getCheckOutDate().equals(filer.getCheckOutDate()) || y.getCheckInDate().isAfter(filer.getCheckInDate()) || y.getCheckOutDate().isBefore(filer.getCheckOutDate())) {
                    System.out.println("Booking is busy");
                } else {
                    responseHotel.add(rooms);
                }
            });
        });


        logger.info("Hotel successfully found and have free room: -> " + hotels);
        if (responseHotel != null) {
            return new ResponseEntity<>(responseHotel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Message.of("All booking is busy: -> "), HttpStatus.NOT_ACCEPTABLE);
        }


    }

    @Override
    public ResponseEntity<?> filter2(Long cityId, LocalDate checkInDate, LocalDate checkOutDate, EBedType bedType) {
        List<Hotel> hotels = hotelDao.findAllByCityAndBedType(cityId, bedType);

        List<Hotel> availableHotels = new ArrayList<>();

        hotels.stream().forEach(x -> {
            List<RoomDto> rooms = roomService.findRoomsByHotel(hotelMapper.toDto(x), bedType);
            List<RoomDto> availableRooms = new ArrayList<>();

            rooms.stream().forEach(y -> {
                List<BookingDto> bookings = bookingService.findAllByRoomAndActive(y, EStatusBooking.ACTIVE);
                if (bookings.isEmpty()) {
                    availableRooms.add(y);
                } else {
                    AtomicBoolean isBooked = new AtomicBoolean(false);
                    bookings.stream().forEach(booking -> {
                        if (checkIsBooked(booking, checkInDate, checkOutDate)) {
                            System.out.println("Room is booked");
                            isBooked.set(true);
                        }
                    });
                    if (isBooked.equals(false)) {
                        availableRooms.add(y);
                    }
                }
            });
            if (!availableRooms.isEmpty()) {
                availableHotels.add(x);
            }
        });
        return new ResponseEntity<>(availableHotels, HttpStatus.OK);
    }




    private boolean checkIsBooked(BookingDto bookingDto, LocalDate startDate, LocalDate endDate) {
        if (startDate.equals(bookingDto.getCheckInDate())
                || startDate.equals(bookingDto.getCheckOutDate())
                || (startDate.isAfter(bookingDto.getCheckInDate()) && startDate.isBefore(bookingDto.getCheckOutDate()))
                || endDate.equals(bookingDto.getCheckInDate())
                || endDate.equals(bookingDto.getCheckOutDate())
                || (endDate.isAfter(bookingDto.getCheckInDate()) && endDate.isBefore(bookingDto.getCheckOutDate()))
                || (startDate.isBefore(bookingDto.getCheckInDate()) && endDate.isAfter(bookingDto.getCheckOutDate()))
        ) {
            return true;
        } else {
            return false;
        }
    }

    /*- список всех отелей по городу и по рейтингу

        - фильтр по – городу
        -- дате заезда и дате выезда
        -- кол-во человек
        -- кол-во номеров
        - фильтр по типу кровати


        - получить полную информацию по отелю с доступными вариантами
        номеров
*/

}


