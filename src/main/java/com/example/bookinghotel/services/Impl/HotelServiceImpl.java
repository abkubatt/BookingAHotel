package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.BookingDao;
import com.example.bookinghotel.dao.HotelDao;
import com.example.bookinghotel.mappers.CityMapper;
import com.example.bookinghotel.mappers.HotelMapper;
import com.example.bookinghotel.models.dtos.BookingDto;
import com.example.bookinghotel.models.dtos.CityDto;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.ReviewDto;
import com.example.bookinghotel.models.entities.Booking;
import com.example.bookinghotel.models.entities.City;
import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.enums.EHotelStatus;
import com.example.bookinghotel.models.request.ToFiler;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.BookingService;
import com.example.bookinghotel.services.CityService;
import com.example.bookinghotel.services.HotelService;
import com.example.bookinghotel.services.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);
    @Autowired
    private HotelDao hotelDao;
//    @Autowired
//    private BookingService bookingService;
    @Autowired
    private CityService cityService;
    private final CityMapper cityMapper  = CityMapper.INSTANCE;
    private final HotelMapper hotelMapper = HotelMapper.INSTANCE;
    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private ReviewService reviewService;

    @Override
    public ResponseEntity<?> save(HotelDto hotelDto) {
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
        if (!isExists){
            logger.error("Hotel not found from database -> " + hotelDto);
            return new ResponseEntity<>(Message.of("Hotel not found"), HttpStatus.NOT_FOUND);
        }else {
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
        if(hotel == null) logger.error("Hotel not found from database: -> " + hotelId);
        logger.info("Hotel successfully found: -> " + hotel);
        return hotelMapper.toDto(hotel);
    }

    @Override
    public ResponseEntity<?> delete(Long hotelId) {
        HotelDto hotelDto = findById(hotelId);
        if (hotelDto == null){
            logger.error("Hotel not found from database: -> " + hotelId);
            return new ResponseEntity<>(Message.of("Hotel not found"),HttpStatus.NOT_FOUND);
        }
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        hotel.setHotelStatus(EHotelStatus.INACTIVE);
        ResponseEntity<?> deletedHotel = update(hotelMapper.toDto(hotel));
        if (deletedHotel.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Hotel successfully deleted: -> " + deletedHotel);
            return new ResponseEntity<>(deletedHotel, HttpStatus.OK);
        }else{
            logger.error("Failed while deleting hotel: -> " + hotelId);
            return new ResponseEntity<>(Message.of("Hotel not deleted"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> blockHotel(Long hotelId) {
        HotelDto hotelDto = findById(hotelId);
        if (hotelDto == null){
            logger.error("Hotel not found from database: -> " + hotelId);
            return new ResponseEntity<>(Message.of("Hotel not found"),HttpStatus.NOT_FOUND);
        }
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        hotel.setHotelStatus(EHotelStatus.BLOCK);
        ResponseEntity<?> blockHotel = update(hotelMapper.toDto(hotel));
        if (blockHotel.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Hotel successfully blocked: -> " + blockHotel);
            return new ResponseEntity<>(blockHotel, HttpStatus.OK);
        }else{
            logger.error("Failed while blocking hotel: -> " + hotel);
            return new ResponseEntity<>(Message.of("Hotel not blocked"), HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<?> confirm(Long hotelId) {
        HotelDto hotelDto = findById(hotelId);
        if (hotelDto == null){
            logger.error("Hotel not found from database: -> " + hotelId);
            return new ResponseEntity<>(Message.of("Hotel not found"),HttpStatus.NOT_FOUND);
        }
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        hotel.setHotelStatus(EHotelStatus.ACTIVE);
        ResponseEntity<?> blockHotel = update(hotelMapper.toDto(hotel));
        if (blockHotel.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Hotel successfully confirmed: -> " + blockHotel);
            return new ResponseEntity<>(blockHotel, HttpStatus.OK);
        }else{
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
        if (cityDto != null){
            List<Hotel> hotels = hotelDao.findAllByCity(cityDto.getId());
            if (!hotels.isEmpty()){
                return hotelMapper.toDtoList(hotels);
            }else{
                return null;
            }
        }else{
            return null;
        }

    }

    @Override
    public void countCurrentScore() {
        List<HotelDto> hotelDtos = findAll();
        hotelDtos.stream().forEach(x->{
            List<ReviewDto> reviews = reviewService.findAllByHotelAndActive(x);
            double sum = reviews.stream().mapToDouble(ReviewDto::getScore).sum();
            double currentScore = Math.round((sum/reviews.size())/10.0)*10;
            x.setCurrentScore(currentScore);
            update(x);
        });
    }

    @Override
    public List<HotelDto> findAll() {
        return hotelMapper.toDtoList(hotelDao.findAll());
    }



    @Override
    @Transactional
    public ResponseEntity<?> filter(ToFiler filer) {

        CityDto cityDto = cityService.findById(filer.getCityId());
        List<Hotel> hotels = hotelDao.findAll(filer.getCityId(), filer.getNumberOfPerson(), filer.getCheckInDate(), filer.getCheckOutDate(),filer.getBedType());
        List<Booking> bookingDto = bookingDao.findAll(filer.getCheckInDate(), filer.getCheckOutDate());
        return new ResponseEntity<>(hotels, HttpStatus.OK);
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
