package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.Exceptions.RoomException;
import com.example.bookinghotel.dao.RoomDao;
import com.example.bookinghotel.mappers.HotelMapper;
import com.example.bookinghotel.mappers.PhotoMapper;
import com.example.bookinghotel.mappers.PriceMapper;
import com.example.bookinghotel.mappers.RoomMapper;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.RoomCategoryDto;
import com.example.bookinghotel.models.dtos.RoomDto;
import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.Room;
import com.example.bookinghotel.models.enums.EBedType;
import com.example.bookinghotel.models.request.ToSaveRoom;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
    @Autowired
    private RoomDao roomDao;
    private final HotelMapper hotelMapper = HotelMapper.INSTANCE;

    @Autowired
    HotelService hotelService;
    private final RoomMapper roomMapper = RoomMapper.INSTANCE;
    @Autowired
    private RoomCategoryService roomCategoryService;
    @Override
    public RoomDto save(RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        room.setActive(true);
        Room saveRoom = roomDao.save(room);
        if (saveRoom == null) logger.error("Failed while saving room: -> " + roomDto);
        logger.info("Room successfully saved: -> " + saveRoom);
        return roomMapper.toDto(saveRoom);
    }


    @Override
    @Transactional
    public ResponseEntity<?> saveRoom(ToSaveRoom saveRoom) throws RoomException {
        try {

            HotelDto hotelDto = hotelService.findById(saveRoom.getHotelId());
            RoomCategoryDto roomCategoryDto = roomCategoryService.findById(saveRoom.getRoomCategoryId());

            RoomDto room = new RoomDto();
            room.setCapacity(saveRoom.getCapacity());
            room.setBedType(saveRoom.getBedType());
            room.setSquare(saveRoom.getSquare());
            room.setWifi(saveRoom.isWifi());
            room.setHotel(hotelDto);
            room.setTypeOfView(saveRoom.getTypeOfView());
            room.setRoomCategory(roomCategoryDto);
            RoomDto savedRoom = save(room);

            return new ResponseEntity<>(savedRoom, HttpStatus.OK);
        }catch (RoomException r){
            logger.error("Failed while saving room(method: saveRoom)");
            RoomException roomException = new RoomException("Failed while saving room(method: saveRoom)");
            return new ResponseEntity<>(roomException.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @Override
    public ResponseEntity<?> update(RoomDto roomDto) {
        boolean isExists = roomDao.existsById(roomDto.getId());
        if (!isExists){
            logger.error("Room not found from database: -> " + roomDto);
            return new ResponseEntity<>(Message.of("Room not found"), HttpStatus.NOT_FOUND);
        }else {
            Room room = roomMapper.toEntity(roomDto);
            Room updatedRoom = roomDao.save(room);
            if (updatedRoom == null) logger.error("Failed while updating room: -> "+ roomDto);
            logger.info("Room successfully updated");
            return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        room.setActive(false);
        ResponseEntity<?> deletedRoom = update(roomMapper.toDto(room));
        if (deletedRoom.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Room successfully deleted: -> "+ deletedRoom);
            return new ResponseEntity<>(deletedRoom, HttpStatus.OK);
        }else{
            logger.error("Failed while deleting room: -> " + roomDto);
            return new ResponseEntity<>(Message.of("Room not deleted"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public RoomDto findById(Long roomId) {
        Room room = roomDao.findById(roomId).orElse(null);
        if (room == null) logger.error("Room not found from database: -> " + room);
        logger.info("Room successfully found from database: -> "+ room);
        return roomMapper.toDto(room);
    }

    @Override
    public RoomDto findByHotel(HotelDto hotelDto) {
        Room room = roomDao.findByHotel(hotelMapper.toEntity(hotelDto));
        return roomMapper.toDto(room);
    }

    @Override
    public List<RoomDto> findRoomsByHotel(HotelDto hotelDto, EBedType bedType) {
        List<Room> rooms = roomDao.findAllByActiveTrueAndHotelAndBedType(hotelMapper.toEntity(hotelDto),bedType);
        return roomMapper.toDtoList(rooms);
    }
}
