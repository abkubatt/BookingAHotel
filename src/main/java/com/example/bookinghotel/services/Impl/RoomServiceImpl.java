package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.RoomDao;
import com.example.bookinghotel.mappers.HotelMapper;
import com.example.bookinghotel.mappers.PhotoMapper;
import com.example.bookinghotel.mappers.PriceMapper;
import com.example.bookinghotel.mappers.RoomMapper;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.PriceDto;
import com.example.bookinghotel.models.dtos.RoomDto;
import com.example.bookinghotel.models.entities.Price;
import com.example.bookinghotel.models.entities.Room;
import com.example.bookinghotel.models.request.SaveRoom;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.HotelService;
import com.example.bookinghotel.services.PriceService;
import com.example.bookinghotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private PriceService priceService;
    @Autowired
    HotelService hotelService;
    private HotelMapper hotelMapper = HotelMapper.INSTANCE;
    private final PriceMapper priceMapper = PriceMapper.INSTANCE;
    private final RoomMapper roomMapper = RoomMapper.INSTANCE;
    private PhotoMapper photoMapper = PhotoMapper.INSTANCE;

    @Override
    @Transactional
    public ResponseEntity<?> save(SaveRoom saveRoom) {
        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(saveRoom.getPrice());
        priceDto.setStartDate(saveRoom.getStartDate());
        priceDto.setEndDate(saveRoom.getEndDate());
        PriceDto savePrice = priceService.save(priceDto);
        HotelDto hotelDto = hotelService.findById(saveRoom.getHotelId());
        if (savePrice != null && hotelDto != null){
            Room room = new Room();
            room.setCapacity(saveRoom.getCapacity());
            room.setBedType(saveRoom.getBedType());
            room.setSquare(saveRoom.getSquare());
            room.setWifi(saveRoom.isWifi());
            room.setHotel(hotelMapper.toEntity(hotelDto));
            room.setTypeOfView(saveRoom.getTypeOfView());
            room.setPhotos(photoMapper.toEntityList(saveRoom.getPhotos()));
            room.setPrice(priceMapper.toEntity(savePrice));

            Room savedRoom = roomDao.save(room);
            if (savedRoom == null){
                return new ResponseEntity<>(savedRoom,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Message.of("Error while saving Room"), HttpStatus.NOT_ACCEPTABLE);
    }


    @Override
    public ResponseEntity<?> update(RoomDto roomDto) {
        boolean isExists = roomDao.existsById(roomDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("Room not found"), HttpStatus.NOT_FOUND);
        }else {
            Room room = roomMapper.toEntity(roomDto);
            Room updatedRoom = roomDao.save(room);
            return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        room.setActive(false);
        ResponseEntity<?> deletedRoom = update(roomMapper.toDto(room));
        if (deletedRoom.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(deletedRoom, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Message.of("Room not deleted"), HttpStatus.NOT_FOUND);
        }
    }
}
