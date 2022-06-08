package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.UserDao;
import com.example.bookinghotel.mappers.UserMapper;
import com.example.bookinghotel.models.dtos.UserDto;
import com.example.bookinghotel.models.entities.User;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setActive(true);
        User saveUser = userDao.save(user);
        return new ResponseEntity<>(saveUser, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(UserDto userDto) {
        boolean isExists = userDao.existsById(userDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("User not found"), HttpStatus.NOT_FOUND);
        }
        else{
            User user = userMapper.toEntity(userDto);
            User updatedUser = userDao.save(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setActive(false);
        ResponseEntity<?> deletedUser = update(userMapper.toDto(user));
        if (deletedUser.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(deletedUser, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Message.of("User not deleted"),HttpStatus.NOT_FOUND);
        }
    }
}
