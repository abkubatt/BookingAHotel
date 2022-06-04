package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.BookHistoryDao;
import com.example.bookinghotel.mappers.BookHistoryMapper;
import com.example.bookinghotel.models.dtos.BookHistoryDto;
import com.example.bookinghotel.models.entities.BookHistory;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.BookHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookHistoryServiceImpl implements BookHistoryService {

    @Autowired
    private BookHistoryDao bookHistoryDao;
    private final BookHistoryMapper bookHistoryMapper = BookHistoryMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(BookHistoryDto bookHistoryDto) {
        BookHistory bookHistory = bookHistoryMapper.toEntity(bookHistoryDto);
        bookHistory.setActive(true);
        BookHistory savedBookHistory = bookHistoryDao.save(bookHistory);
        return new ResponseEntity<>(Message.of("BookHistory saved"), HttpStatus.OK);
    }
}
