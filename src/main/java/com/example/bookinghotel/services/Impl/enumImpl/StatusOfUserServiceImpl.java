package com.example.bookinghotel.services.Impl.enumImpl;

import com.example.bookinghotel.dao.enumDao.StatusOfUserDao;
import com.example.bookinghotel.mappers.enumMappers.StatusOfUserMapper;
import com.example.bookinghotel.models.dtos.enumdtos.StatusOfUserDto;
import com.example.bookinghotel.models.entities.enumentities.StatusOfUser;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.enumService.StatusOfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StatusOfUserServiceImpl implements StatusOfUserService {
    @Autowired
    private StatusOfUserDao statusOfUserDao;
    private final StatusOfUserMapper statusOfUserMapper = StatusOfUserMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(StatusOfUserDto statusOfUserDto) {
        StatusOfUser statusOfUser = statusOfUserMapper.toEntity(statusOfUserDto);
        StatusOfUser saveStatusOfUser = statusOfUserDao.save(statusOfUser);
        return new ResponseEntity<>(Message.of("StatusOfUser Saved"), HttpStatus.OK);
    }
}
