package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.UserDao;
import com.example.bookinghotel.mappers.UserMapper;
import com.example.bookinghotel.models.dtos.UserDto;
import com.example.bookinghotel.models.entities.User;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setActive(true);
        User saveUser = userDao.save(user);
        if (saveUser == null) logger.error("Failed while saving user: -> " + userDto);
        logger.info("User successfully saved: -> "+ saveUser);
        return new ResponseEntity<>(saveUser, HttpStatus.OK);
    }

    @Override
    public UserDto saveInProject(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setActive(true);
        User saveUser = userDao.save(user);
        if (saveUser == null) logger.error("Failed while saving user: -> " + userDto);
        logger.info("User successfully saved: -> "+ saveUser);
        return userMapper.toDto(saveUser);
    }

    @Override
    public ResponseEntity<?> update(UserDto userDto) {
        boolean isExists = userDao.existsById(userDto.getId());
        if (!isExists){
            logger.error("User not found from database: -> " + userDto);
            return new ResponseEntity<>(Message.of("User not found"), HttpStatus.NOT_FOUND);
        }
        else{
            User user = userMapper.toEntity(userDto);
            User updatedUser = userDao.save(user);
            if (updatedUser == null) logger.error("Failed while updating user: -> " + userDto);
            logger.info("User successfully updated: -> "+ updatedUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(Long userId) {
        UserDto userDto = findById(userId);
        User user = userMapper.toEntity(userDto);
        user.setActive(false);
        ResponseEntity<?> deletedUser = update(userMapper.toDto(user));
        if (deletedUser.getStatusCode().equals(HttpStatus.OK)){
            logger.info("User successfully deleted: -> " + deletedUser);
            return new ResponseEntity<>(deletedUser, HttpStatus.OK);
        }else{
            logger.error("Failed while deleting user: -> " + userDto);
            return new ResponseEntity<>(Message.of("User not deleted"),HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public UserDto findById(Long userId) {
        User user = userDao.findById(userId).orElse(null);
        if (user == null) logger.error("User not found from database: -> " + userId);
        logger.info("User successfully found from database: -> " + user);
        return userMapper.toDto(user);
    }
}
