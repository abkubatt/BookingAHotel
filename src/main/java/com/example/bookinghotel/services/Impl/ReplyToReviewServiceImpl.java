package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.ReplyToReviewDao;
import com.example.bookinghotel.mappers.ReplyToReviewMapper;
import com.example.bookinghotel.models.dtos.ReplyToReviewDto;
import com.example.bookinghotel.models.entities.ReplyToReview;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.ReplyToReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReplyToReviewServiceImpl implements ReplyToReviewService {
    @Autowired
    private ReplyToReviewDao replyToReviewDao;
    private final ReplyToReviewMapper replyToReviewMapper = ReplyToReviewMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(ReplyToReviewDto replyToReviewDto) {
        ReplyToReview replyToReview = replyToReviewMapper.toEntity(replyToReviewDto);
        replyToReview.setDate(LocalDate.now());
        ReplyToReview saveReplyToReview = replyToReviewDao.save(replyToReview);
        return new ResponseEntity<>(saveReplyToReview, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(ReplyToReviewDto replyToReviewDto) {
        ReplyToReview replyToReview = replyToReviewMapper.toEntity(replyToReviewDto);
        replyToReviewDao.deleteById(replyToReview.getId());
        return new ResponseEntity<>(Message.of("ReplyToReview deleted"), HttpStatus.OK);
    }
}
