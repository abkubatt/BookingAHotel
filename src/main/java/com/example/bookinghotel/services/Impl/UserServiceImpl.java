package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.UserDao;
import com.example.bookinghotel.mappers.UserMapper;
import com.example.bookinghotel.models.dtos.UserDto;
import com.example.bookinghotel.models.entities.User;
import com.example.bookinghotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public UserDto save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userDao.save(user));
    }
}
