package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.RoomDao;
import com.example.bookinghotel.mappers.RoomMapper;
import com.example.bookinghotel.models.dtos.RoomDto;
import com.example.bookinghotel.models.entities.Room;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;
    private final RoomMapper roomMapper = RoomMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        Room saveRoom = roomDao.save(room);
        return new ResponseEntity<>(Message.of("Room saved"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(RoomDto roomDto) {
        boolean isExists = roomDao.existsById(roomDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("Room not found"), HttpStatus.NOT_FOUND);
        }else {
            Room room = roomMapper.toEntity(roomDto);
            Room updatedRoom = roomDao.save(room);
            return new ResponseEntity<>(Message.of("Room updated"), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        room.setActive(false);
        ResponseEntity<?> deletedRoom = update(roomMapper.toDto(room));
        return new ResponseEntity<>(Message.of("Room deleted"), HttpStatus.OK);
    }
}
