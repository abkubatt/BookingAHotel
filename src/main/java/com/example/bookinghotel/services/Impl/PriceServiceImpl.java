package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.PriceDao;
import com.example.bookinghotel.mappers.PriceMapper;
import com.example.bookinghotel.models.dtos.PriceDto;
import com.example.bookinghotel.models.entities.Price;
import com.example.bookinghotel.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService {
    @Autowired
    private PriceDao priceDao;
    private final PriceMapper priceMapper = PriceMapper.INSTANCE;

    @Override
    public PriceDto save(PriceDto priceDto) {
        Price price = priceMapper.toEntity(priceDto);
        return priceMapper.toDto(priceDao.save(price));
    }
}
