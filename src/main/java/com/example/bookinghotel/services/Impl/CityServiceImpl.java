package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.CityDao;
import com.example.bookinghotel.mappers.CityMapper;
import com.example.bookinghotel.models.dtos.CityDto;
import com.example.bookinghotel.models.entities.City;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;
    private final CityMapper cityMapper = CityMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(CityDto cityDto) {
        City city = cityMapper.toEntity(cityDto);
        city.setActive(true);
        City saveCity = cityDao.save(city);
        return new ResponseEntity<>(saveCity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(CityDto cityDto) {
        boolean isExists = cityDao.existsById(cityDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("City not found"), HttpStatus.NOT_FOUND);
        }else{
            City city = cityMapper.toEntity(cityDto);
            City updatedCity = cityDao.save(city);
            return new ResponseEntity<>(updatedCity, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(CityDto cityDto) {
        City city = cityMapper.toEntity(cityDto);
        city.setActive(false);
        ResponseEntity<?> cityDeleted = update(cityMapper.toDto(city));
        if(cityDeleted.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(Message.of("city deleted"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Message.of("City not deleted"), HttpStatus.NOT_FOUND);

        }
    }
}
