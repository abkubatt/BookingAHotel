package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.HotelDao;
import com.example.bookinghotel.mappers.HotelMapper;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.BookingService;
import com.example.bookinghotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private BookingService bookingService;
    private final HotelMapper hotelMapper = HotelMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(HotelDto hotelDto) {
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        hotel.setActive(true);
        Hotel saveHotel = hotelDao.save(hotel);
        return new ResponseEntity<>(saveHotel, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(HotelDto hotelDto) {
        boolean isExists = hotelDao.existsById(hotelDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("Hotel not found"), HttpStatus.NOT_FOUND);
        }else {
            Hotel hotel = hotelMapper.toEntity(hotelDto);
            Hotel updatedHotel = hotelDao.save(hotel);
            return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(HotelDto hotelDto) {
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        hotel.setActive(false);
        ResponseEntity<?> deletedHotel = update(hotelMapper.toDto(hotel));
        if (deletedHotel.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(deletedHotel, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Message.of("Hotel not deleted"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> findAllCityByRating(HotelDto hotelDto) {
        return null;
    }
}
