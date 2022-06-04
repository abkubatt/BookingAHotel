package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.ReviewDao;
import com.example.bookinghotel.mappers.ReviewMapper;
import com.example.bookinghotel.models.dtos.ReviewDto;
import com.example.bookinghotel.models.entities.Review;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;
    private final ReviewMapper reviewMapper = ReviewMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        review.setActive(true);
        Review saveReview = reviewDao.save(review);
        return new ResponseEntity<>(Message.of("Review saved"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(ReviewDto reviewDto) {
        boolean isExists = reviewDao.existsById(reviewDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("Review not found"), HttpStatus.NOT_FOUND);
        }else {
            Review review = reviewMapper.toEntity(reviewDto);
            Review updatedReview = reviewDao.save(review);
            return new ResponseEntity<>(Message.of("Review updated"), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        review.setActive(false);
        ResponseEntity<?> deletedReview = update(reviewMapper.toDto(review));
        return new ResponseEntity<>(Message.of("Review deleted"), HttpStatus.OK);
    }
}
