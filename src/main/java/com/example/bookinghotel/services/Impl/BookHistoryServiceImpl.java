package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.BookHistoryDao;
import com.example.bookinghotel.mappers.BookHistoryMapper;
import com.example.bookinghotel.models.dtos.BookHistoryDto;
import com.example.bookinghotel.models.entities.BookHistory;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.BookHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;

@Service
public class BookHistoryServiceImpl implements BookHistoryService {

    @Autowired
    private BookHistoryDao bookHistoryDao;
    Logger logger = LoggerFactory.getLogger(BookHistoryServiceImpl.class);
    private final BookHistoryMapper bookHistoryMapper = BookHistoryMapper.INSTANCE;

    @Override
    @Transactional
    public ResponseEntity<?> save(BookHistoryDto bookHistoryDto) {
        BookHistory bookHistory = bookHistoryMapper.toEntity(bookHistoryDto);
        bookHistory.setActive(true);
        BookHistory savedBookHistory = bookHistoryDao.save(bookHistory);
        if (savedBookHistory == null){
            logger.error("BookingHistory not saved: -> " + bookHistoryDto);
        }
        logger.info("BookingHistory saved: -> " + savedBookHistory);
        return new ResponseEntity<>(savedBookHistory, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(BookHistoryDto bookHistoryDto) {
        boolean isExists = bookHistoryDao.existsById(bookHistoryDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("BookHistory is not found"), HttpStatus.NOT_FOUND);
        }else{
            BookHistory bookHistory = bookHistoryMapper.toEntity(bookHistoryDto);
            BookHistory updatedBookHistory = bookHistoryDao.save(bookHistory);
            return new ResponseEntity<>(updatedBookHistory, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(BookHistoryDto bookHistoryDto) {
        BookHistory bookHistory = bookHistoryMapper.toEntity(bookHistoryDto);
        bookHistory.setActive(false);
        ResponseEntity<?> deletedBookHistory = update(bookHistoryMapper.toDto(bookHistory));
        if (deletedBookHistory.getStatusCode().equals(HttpStatus.OK)){
            logger.info("BookHistory deleted: -> " + deletedBookHistory);
            return new ResponseEntity<>(deletedBookHistory, HttpStatus.OK);
        }else{
            logger.error("BookHistory not deleted: -> " + bookHistoryDto);
            return new ResponseEntity<>(Message.of("BookHistory not deleted"), HttpStatus.NOT_FOUND);
        }
    }
}
