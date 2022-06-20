package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.RoomDao;
import com.example.bookinghotel.mappers.HotelMapper;
import com.example.bookinghotel.mappers.PhotoMapper;
import com.example.bookinghotel.mappers.PriceMapper;
import com.example.bookinghotel.mappers.RoomMapper;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.PriceDto;
import com.example.bookinghotel.models.dtos.RoomDto;
import com.example.bookinghotel.models.entities.Room;
import com.example.bookinghotel.models.request.ToSaveRoom;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.HotelService;
import com.example.bookinghotel.services.PhotoService;
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
    @Autowired private PhotoService photoService;
    @Autowired
    HotelService hotelService;
    private HotelMapper hotelMapper = HotelMapper.INSTANCE;
    private final PriceMapper priceMapper = PriceMapper.INSTANCE;
    private final RoomMapper roomMapper = RoomMapper.INSTANCE;
    private final PhotoMapper photoMapper = PhotoMapper.INSTANCE;
    @Override
    public RoomDto save(RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);
        room.setActive(true);
        Room saveRoom = roomDao.save(room);
        return roomMapper.toDto(saveRoom);
    }


    @Override
    @Transactional
    public ResponseEntity<?> saveRoom(ToSaveRoom saveRoom) {
        HotelDto hotelDto = hotelService.findById(saveRoom.getHotelId());

        RoomDto room = new RoomDto();
        room.setCapacity(saveRoom.getCapacity());
        room.setBedType(saveRoom.getBedType());
        room.setSquare(saveRoom.getSquare());
        room.setWifi(saveRoom.isWifi());
        room.setHotel(hotelDto);
        room.setTypeOfView(saveRoom.getTypeOfView());
        RoomDto savedRoom = save(room);


        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(saveRoom.getPrice());
        priceDto.setStartDate(saveRoom.getStartDate());
        priceDto.setEndDate(saveRoom.getEndDate());
        priceDto.setRoom(savedRoom);
        PriceDto savedPrice = priceService.save(priceDto);

        return new ResponseEntity<>(savedRoom, HttpStatus.OK);
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
