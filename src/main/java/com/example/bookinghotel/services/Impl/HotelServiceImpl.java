package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.HotelDao;
import com.example.bookinghotel.mappers.HotelMapper;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelDao hotelDao;
    private final HotelMapper hotelMapper = HotelMapper.INSTANCE;

    @Override
    public HotelDto save(HotelDto hotelDto) {
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        return hotelMapper.toDto(hotelDao.save(hotel));
    }
}
