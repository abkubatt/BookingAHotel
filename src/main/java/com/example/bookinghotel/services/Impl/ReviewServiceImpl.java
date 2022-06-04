package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.ReviewDao;
import com.example.bookinghotel.mappers.ReviewMapper;
import com.example.bookinghotel.models.dtos.ReviewDto;
import com.example.bookinghotel.models.entities.Review;
import com.example.bookinghotel.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;
    private final ReviewMapper reviewMapper = ReviewMapper.INSTANCE;

    @Override
    public ReviewDto save(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        return reviewMapper.toDto(reviewDao.save(review));
    }
}
