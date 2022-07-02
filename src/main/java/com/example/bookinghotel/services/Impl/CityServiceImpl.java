package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.CityDao;
import com.example.bookinghotel.mappers.CityMapper;
import com.example.bookinghotel.models.dtos.CityDto;
import com.example.bookinghotel.models.entities.City;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);
    @Autowired
    private CityDao cityDao;
    private final CityMapper cityMapper = CityMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(CityDto cityDto) {
        City city = cityMapper.toEntity(cityDto);
        city.setActive(true);
        City saveCity = cityDao.save(city);
        if (saveCity == null) logger.error("City not saved: -> " + cityDto);
        logger.info("City successfully saved: -> " + saveCity);
        return new ResponseEntity<>(saveCity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(CityDto cityDto) {
        boolean isExists = cityDao.existsById(cityDto.getId());
        if (!isExists){
            logger.error("City not found from database: -> " + cityDto);
            return new ResponseEntity<>(Message.of("City not found"), HttpStatus.NOT_FOUND);
        }else{
            City city = cityMapper.toEntity(cityDto);
            City updatedCity = cityDao.save(city);
            if (updatedCity == null) logger.error("City not updated: -> " + cityDto);

            logger.info("City successfully updated: -> " + updatedCity);
            return new ResponseEntity<>(updatedCity, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(CityDto cityDto) {
        City city = cityMapper.toEntity(cityDto);
        city.setActive(false);
        ResponseEntity<?> cityDeleted = update(cityMapper.toDto(city));
        if(cityDeleted.getStatusCode().equals(HttpStatus.OK)){
            logger.info("City successfully deleted: -> " + cityDeleted);
            return new ResponseEntity<>(cityDeleted, HttpStatus.OK);
        }else{
            logger.error("Failed while updating city: -> " + cityDto);
            return new ResponseEntity<>(Message.of("City not deleted"), HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public CityDto findById(Long cityId) {
        City city = cityDao.findById(cityId).orElse(null);
        if (city != null){
            logger.info("City successfully found from database: -> " + city);
            return cityMapper.toDto(city);
        }else{
            logger.error("City not found from database: -> " + cityId);
            return null;
        }
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<City> cities = cityDao.findAllByName();
        if (cities == null) logger.error("Failed while finding cities");
        logger.info("Cities successfully found from database" + cities);
        return new ResponseEntity<>(cityMapper.toDtoList(cities), HttpStatus.OK);
    }
}
