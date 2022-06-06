package com.example.bookinghotel.services.Impl.enumImpl;

import com.example.bookinghotel.dao.enumDao.TypeOfRoomDao;
import com.example.bookinghotel.mappers.enumMappers.TypeOfRoomMapper;
import com.example.bookinghotel.models.dtos.enumdtos.TypeOfRoomDto;
import com.example.bookinghotel.models.entities.enumentities.TypeOfRoom;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.enumService.TypeOfRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TypeOfRoomServiceImpl implements TypeOfRoomService {

    @Autowired
    private TypeOfRoomDao typeOfRoomDao;
    private final TypeOfRoomMapper typeOfRoomMapper = TypeOfRoomMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(TypeOfRoomDto typeOfRoomDto) {
        TypeOfRoom typeOfRoom = typeOfRoomMapper.toEntity(typeOfRoomDto);
        TypeOfRoom saveTypeOfRoom = typeOfRoomDao.save(typeOfRoom);
        return new ResponseEntity<>(Message.of("TypeOfRoom saved"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(TypeOfRoomDto typeOfRoomDto) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(TypeOfRoomDto typeOfRoomDto) {
        typeOfRoomDao.deleteById(typeOfRoomDto.getId());
        return new ResponseEntity<>(Message.of("TypeOfRoom deleted"), HttpStatus.OK);
    }
}
