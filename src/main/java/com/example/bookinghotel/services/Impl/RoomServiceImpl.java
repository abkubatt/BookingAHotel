package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.RoomDao;
import com.example.bookinghotel.mappers.RoomMapper;
import com.example.bookinghotel.models.dtos.RoomDto;
import com.example.bookinghotel.models.entities.Room;
import com.example.bookinghotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;
    private final RoomMapper roomMapper = RoomMapper.INSTANCE;

    @Override
    public RoomDto save(RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        return roomMapper.toDto(roomDao.save(room));
    }
}
