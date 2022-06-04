package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.CityDao;
import com.example.bookinghotel.mappers.CityMapper;
import com.example.bookinghotel.models.dtos.CityDto;
import com.example.bookinghotel.models.entities.City;
import com.example.bookinghotel.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;
    private final CityMapper cityMapper = CityMapper.INSTANCE;

    @Override
    public CityDto save(CityDto cityDto) {
        City city = cityMapper.toEntity(cityDto);
        return cityMapper.toDto(cityDao.save(city));
    }
}
