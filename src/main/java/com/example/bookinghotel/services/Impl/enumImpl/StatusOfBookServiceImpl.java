package com.example.bookinghotel.services.Impl.enumImpl;

import com.example.bookinghotel.dao.enumDao.StatusOfBookDao;
import com.example.bookinghotel.mappers.enumMappers.StatusOfBookMapper;
import com.example.bookinghotel.models.dtos.enumdtos.StatusOfBookDto;
import com.example.bookinghotel.models.entities.enumentities.StatusOfBook;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.enumService.StatusOfBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StatusOfBookServiceImpl implements StatusOfBookService {
    @Autowired
    private StatusOfBookDao statusOfBookDao;
    private final StatusOfBookMapper statusOfBookMapper = StatusOfBookMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(StatusOfBookDto statusOfBookDto) {
        StatusOfBook statusOfBook = statusOfBookMapper.toEntity(statusOfBookDto);
        StatusOfBook saveStatusOfBook = statusOfBookDao.save(statusOfBook);
        return new ResponseEntity<>(Message.of("StatusOfBook saved"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(StatusOfBookDto statusOfBookDto) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(StatusOfBookDto statusOfBookDto) {
        statusOfBookDao.deleteById(statusOfBookDto.getId());
        return new ResponseEntity<>(Message.of("StatusOfBook deleted"), HttpStatus.OK);
    }
}

