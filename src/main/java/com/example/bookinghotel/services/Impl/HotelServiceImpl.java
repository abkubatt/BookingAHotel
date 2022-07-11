package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.BookingDao;
import com.example.bookinghotel.dao.HotelDao;
import com.example.bookinghotel.mappers.CityMapper;
import com.example.bookinghotel.mappers.HotelMapper;
import com.example.bookinghotel.mappers.RoomMapper;
import com.example.bookinghotel.mappers.UserMapper;
import com.example.bookinghotel.models.dtos.*;
import com.example.bookinghotel.models.entities.*;
import com.example.bookinghotel.models.enums.EBedType;
import com.example.bookinghotel.models.enums.EHotelStatus;
import com.example.bookinghotel.models.enums.ERole;
import com.example.bookinghotel.models.enums.EStatusBooking;
import com.example.bookinghotel.models.request.ToFiler;
import com.example.bookinghotel.models.response.HotelFilterResponse;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.models.response.RoomFilterResponse;
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

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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
    private CityService cityService;
    private final HotelMapper hotelMapper = HotelMapper.INSTANCE;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private PriceService priceService;
    private final UserMapper userMapper = UserMapper.INSTANCE;



    @Override
    @Transactional
    public ResponseEntity<?> save(HotelDto hotelDto) {
        UserDto userDto = hotelDto.getManager();
        userDto.setRole(ERole.MANAGER);
        UserDto savedUser = userService.saveInProject(userDto);
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        hotel.setManager(userMapper.toEntity(savedUser));
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
    public ResponseEntity<?> Rating(List<HotelFilterResponse> hotels) {

        List<HotelFilterResponse> sortedList = hotels.stream().sorted(Comparator.comparing(HotelFilterResponse::getCurrentScore).reversed()).collect(Collectors.toList());
        logger.info("Method reverse Sorting : -> " + LocalDate.now() + " " + sortedList);
        return new ResponseEntity<>(sortedList,HttpStatus.OK);

    }


    @Override
    public List<HotelDto> findAllHotelsByCity(Long cityId) {
        CityDto cityDto = cityService.findById(cityId);
        if (cityDto != null) {
            List<Hotel> hotels = hotelDao.findAllByCity(cityDto.getId());
            if (!hotels.isEmpty()) {
                logger.info("FindAllHotelByCity: ->  " + hotels);
                return hotelMapper.toDtoList(hotels);
            } else {
                logger.error("Hotel not found in this city: " + cityId);
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
            List<ReviewDto> reviews = reviewService.findAllByHotelAndActive(x.getId());
            double sum = reviews.stream().mapToDouble(ReviewDto::getScore).sum();
            double currentScore = Math.round((sum / reviews.size()));
            x.setCurrentScore(currentScore);
            update(x);
        });
    }

    @Override
    public List<HotelDto> findAll() {
        List<Hotel> hotels = hotelDao.findAllHotel();
        return hotelMapper.toDtoList(hotels);
    }

    @Override
    @Transactional
    public ResponseEntity<?> filter(Long cityId, LocalDate checkInDate, LocalDate checkOutDate, EBedType bedType, int capacity) {
        List<Hotel> hotels = hotelDao.findAllByCityAndBedType(cityId, bedType);
        List<HotelFilterResponse> filteredHotels = new ArrayList<>();


        List<Hotel> availableHotels = new ArrayList<>();

        hotels.stream().forEach(x -> {
            List<RoomDto> rooms = roomService.findRoomsByHotel(hotelMapper.toDto(x), bedType, capacity);
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
            HotelFilterResponse hotelFilterResponse = formHotelResponse(x, availableRooms, checkInDate, checkOutDate);
            filteredHotels.add(hotelFilterResponse);


        });
        logger.info("Result of filter method: -> " + filteredHotels);
        return new ResponseEntity<>(filteredHotels, HttpStatus.OK);

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


    private HotelFilterResponse formHotelResponse(Hotel hotel, List<RoomDto> rooms, LocalDate checkIn, LocalDate checkOut) {
        HotelFilterResponse hotelResponse = new HotelFilterResponse();
        hotelResponse.setId(hotel.getId());
        hotelResponse.setDescription(hotel.getDescription());
        hotelResponse.setAddress(hotel.getAddress());
        hotelResponse.setEmail(hotel.getEmail());
        hotelResponse.setCurrentScore(hotel.getCurrentScore());
        hotelResponse.setPhone(hotel.getPhone());
        hotelResponse.setName(hotel.getName());
        hotelResponse.setCurrentScore(hotel.getCurrentScore());

        List<RoomFilterResponse> roomResponse = new ArrayList<>();
        rooms.stream().forEach(room -> {
            PriceDto priceCheckIn = priceService.findPrice(room.getRoomCategory(), checkIn);
            PriceDto priceCheckOut = priceService.findPrice(room.getRoomCategory(), checkOut);
            if (priceCheckIn != null && priceCheckOut != null) {
                if (priceCheckIn.getPrice() == priceCheckOut.getPrice()) {
                    Duration diff = Duration.between(checkIn.atStartOfDay(), checkOut.atStartOfDay());

                    long diffDays = diff.toDays();
                    diffDays += 1;
                    float countPrice = diffDays * priceCheckIn.getPrice();

                    RoomFilterResponse roomFilterResponse = RoomFilterResponse.builder()
                            .bedType(room.getBedType())
                            .capacity(room.getCapacity())
                            .checkInDate(checkIn)
                            .checkOutDate(checkOut)
                            .id(room.getId())
                            .square(room.getSquare())
                            .typeOfView(room.getTypeOfView())
                            .wifi(room.isWifi())
                            .totalSum(countPrice)
                            .build();
                    roomResponse.add(roomFilterResponse);
                } else {
                    Duration diff = Duration.between(checkIn.atStartOfDay(), priceCheckIn.getEndDate());

                    long diffDays = diff.toDays();
                    diffDays += 1;

                    float sumBeginning = diffDays * priceCheckIn.getPrice();

                    Duration diff2 = Duration.between(priceCheckOut.getStartDate(), checkOut);

                    long diffDays2 = diff.toDays();
                    diffDays2 += 1;

                    float sumEnding = diffDays2 * priceCheckOut.getPrice();


                    float totalSum = sumBeginning + sumEnding;


                    RoomFilterResponse roomFilterResponse = RoomFilterResponse.builder()
                            .bedType(room.getBedType())
                            .capacity(room.getCapacity())
                            .checkInDate(checkIn)
                            .checkOutDate(checkOut)
                            .id(room.getId())
                            .square(room.getSquare())
                            .typeOfView(room.getTypeOfView())
                            .wifi(room.isWifi())
                            .totalSum(totalSum)
                            .build();
                    roomResponse.add(roomFilterResponse);
                }
            }
        });


        hotelResponse.setAvailableRooms(roomResponse);

        logger.info("Status of HotelFilterResponse while method working: ->" + hotelResponse);
        return hotelResponse;

    }
}





