package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.PriceDao;
import com.example.bookinghotel.mappers.PriceMapper;
import com.example.bookinghotel.mappers.RoomCategoryMapper;
import com.example.bookinghotel.models.dtos.PriceDto;
import com.example.bookinghotel.models.dtos.RoomCategoryDto;
import com.example.bookinghotel.models.entities.Price;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PriceServiceImpl implements PriceService {
    Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);
    @Autowired
    private PriceDao priceDao;
    private final PriceMapper priceMapper = PriceMapper.INSTANCE;

    @Override
    public PriceDto save(PriceDto priceDto){
        Price price = priceMapper.toEntity(priceDto);
        Price savedPrice = priceDao.save(price);
        if (savedPrice == null) logger.error("Failed while saving price: -> " + priceDto);
        logger.info("Price successfully saved: -> "+ savedPrice);
        return priceMapper.toDto(savedPrice);
    }

    @Override
    public ResponseEntity<?> update(PriceDto priceDto) {
        boolean isExists = priceDao.existsById(priceDto.getId());
        if (!isExists){
            logger.error("Price not found from database: -> " + priceDto);
            return new ResponseEntity<>(Message.of("Price not found"), HttpStatus.NOT_FOUND);
        }else {
            Price price = priceMapper.toEntity(priceDto);
            Price updatedPrice = priceDao.save(price);
            if (updatedPrice == null) logger.error("Failed while updating price: -> " + price);
            logger.info("Price successfully updated: -> " + updatedPrice);
            return new ResponseEntity<>(updatedPrice, HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> delete(Long priceId) {
        PriceDto priceDto = findById(priceId);
        Price price = priceMapper.toEntity(priceDto);
        ResponseEntity<?> deletedPrice = update(priceMapper.toDto(price));
        if (deletedPrice.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Price successfully deleted: -> " + deletedPrice);
            return new ResponseEntity<>(deletedPrice, HttpStatus.OK);
        }else{
            logger.error("Failed while deleting price: -> " + priceDto);
            return new ResponseEntity<>(Message.of("Price not deleted"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public PriceDto findPrice(RoomCategoryDto roomCategoryDto,LocalDate date) {
        Price price = priceDao.findByRoomCategoryAndStartDateAndEndDate(roomCategoryDto.getId(),date);
        return priceMapper.toDto(price);
    }

    @Override
    public PriceDto findById(Long id) {
        Price price = priceDao.findById(id).orElse(null);
        return priceMapper.toDto(price);
    }
}
