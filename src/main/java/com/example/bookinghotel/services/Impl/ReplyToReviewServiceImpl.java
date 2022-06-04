package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.ReplyToReviewDao;
import com.example.bookinghotel.mappers.ReplyToReviewMapper;
import com.example.bookinghotel.models.dtos.ReplyToReviewDto;
import com.example.bookinghotel.models.entities.ReplyToReview;
import com.example.bookinghotel.services.ReplyToReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyToReviewServiceImpl implements ReplyToReviewService {
    @Autowired
    private ReplyToReviewDao replyToReviewDao;
    private final ReplyToReviewMapper replyToReviewMapper = ReplyToReviewMapper.INSTANCE;

    @Override
    public ReplyToReviewDto save(ReplyToReviewDto replyToReviewDto) {
        ReplyToReview replyToReview = replyToReviewMapper.toEntity(replyToReviewDto);
        return replyToReviewMapper.toDto(replyToReviewDao.save(replyToReview));
    }
}
