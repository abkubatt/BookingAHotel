package com.example.bookinghotel.services.Impl.enumImpl;

import com.example.bookinghotel.dao.enumDao.TypeOfRoomDao;
import com.example.bookinghotel.mappers.enumMappers.TypeOfRoomMapper;
import com.example.bookinghotel.models.dtos.enumdtos.TypeOfRoomDto;
import com.example.bookinghotel.models.entities.enumentities.TypeOfRoom;
import com.example.bookinghotel.services.enumService.TypeOfRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeOfRoomServiceImpl implements TypeOfRoomService {

    @Autowired
    private TypeOfRoomDao typeOfRoomDao;
    private final TypeOfRoomMapper typeOfRoomMapper = TypeOfRoomMapper.INSTANCE;

    @Override
    public TypeOfRoomDto save(TypeOfRoomDto typeOfRoomDto) {
        TypeOfRoom typeOfRoom = typeOfRoomMapper.toEntity(typeOfRoomDto);
        return typeOfRoomMapper.toDto(typeOfRoomDao.save(typeOfRoom));
    }
}
