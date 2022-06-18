package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.PriceDao;
import com.example.bookinghotel.mappers.PriceMapper;
import com.example.bookinghotel.models.dtos.PriceDto;
import com.example.bookinghotel.models.entities.Price;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService {
    @Autowired
    private PriceDao priceDao;
    private final PriceMapper priceMapper = PriceMapper.INSTANCE;

    @Override
    public PriceDto save(PriceDto priceDto) {
        Price price = priceMapper.toEntity(priceDto);
        price.setActive(true);
        Price savePrice = priceDao.save(price);
        return priceMapper.toDto(savePrice);
    }

    @Override
    public ResponseEntity<?> update(PriceDto priceDto) {
        boolean isExists = priceDao.existsById(priceDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("Price not found"), HttpStatus.NOT_FOUND);
        }else {
            Price price = priceMapper.toEntity(priceDto);
            Price updatedPrice = priceDao.save(price);
            return new ResponseEntity<>(updatedPrice, HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> delete(PriceDto priceDto) {
        Price price = priceMapper.toEntity(priceDto);
        price.setActive(false);
        ResponseEntity<?> deletedPrice = update(priceMapper.toDto(price));
        if (deletedPrice.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(deletedPrice, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Message.of("Price not deleted"), HttpStatus.NOT_FOUND);
        }
    }
}
