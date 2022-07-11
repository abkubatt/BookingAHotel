package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.RoomCategoryDao;
import com.example.bookinghotel.mappers.PriceMapper;
import com.example.bookinghotel.mappers.RoomCategoryMapper;
import com.example.bookinghotel.models.dtos.PriceDto;
import com.example.bookinghotel.models.dtos.RoomCategoryDto;
import com.example.bookinghotel.models.entities.Room;
import com.example.bookinghotel.models.entities.RoomCategory;
import com.example.bookinghotel.models.request.ToSaveCategoryAndPrice;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.PriceService;
import com.example.bookinghotel.services.RoomCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomCategoryServiceImpl implements RoomCategoryService {
    Logger logger = LoggerFactory.getLogger(RoomCategoryServiceImpl.class);
    @Autowired
    private RoomCategoryDao roomCategoryDao;
    private final RoomCategoryMapper roomCategoryMapper = RoomCategoryMapper.INSTANCE;
    @Autowired
    private PriceService priceService;



    @Override
    public RoomCategoryDto save(RoomCategoryDto roomCategoryDto) {
        RoomCategory roomCategory = roomCategoryMapper.toEntity(roomCategoryDto);
        roomCategory.setActive(true);
        RoomCategory savedRoomCategory = roomCategoryDao.save(roomCategory);
        if (savedRoomCategory == null){
            logger.error("Failed while saving category: -> " + roomCategoryDto);
            return null;
        }
        logger.info("RoomCategory successfully saved: -> " + savedRoomCategory);
        return roomCategoryMapper.toDto(savedRoomCategory);
    }

    @Override
    @Transactional
    public ResponseEntity<?> saveCategoryAndPrice(ToSaveCategoryAndPrice saveCategoryAndPrice) {
            RoomCategory roomCategory = new RoomCategory();
            roomCategory.setTypeOfRoom(saveCategoryAndPrice.getTypeOfRoom());
            RoomCategoryDto savedRoomCategory = save(roomCategoryMapper.toDto(roomCategory));



            PriceDto priceDto = new PriceDto();
            priceDto.setPrice(saveCategoryAndPrice.getPrice());
            priceDto.setStartDate(saveCategoryAndPrice.getStartDate());
            priceDto.setEndDate(saveCategoryAndPrice.getEndDate());
            priceDto.setRoomCategory(savedRoomCategory);
            PriceDto savedPrice = priceService.save(priceDto);

            logger.info("SaveCategoryAndPrice successfully saved: -> " + roomCategory + " price: -> " + priceDto);
            return new ResponseEntity<>(savedPrice, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(RoomCategoryDto roomCategoryDto) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(RoomCategoryDto roomCategoryDto) {
        return null;
    }

    @Override
    public RoomCategoryDto findById(Long roomCategoryId) {
        RoomCategory roomCategory = roomCategoryDao.findById(roomCategoryId).orElse(null);
        return roomCategoryMapper.toDto(roomCategory);

    }
}
