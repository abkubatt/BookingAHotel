package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.ReviewDao;
import com.example.bookinghotel.mappers.HotelMapper;
import com.example.bookinghotel.mappers.ReviewMapper;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.ReviewDto;
import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.Review;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.HotelService;
import com.example.bookinghotel.services.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Autowired
    private ReviewDao reviewDao;
    private final ReviewMapper reviewMapper = ReviewMapper.INSTANCE;
    private HotelMapper hotelMapper = HotelMapper.INSTANCE;
    @Autowired
    private HotelService hotelService;

    @Override
    public ResponseEntity<?> save(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        review.setActive(true);
        review.setDate(LocalDate.now());
        Review saveReview = reviewDao.save(review);
        if (saveReview == null) logger.error("Failed while saving review: -> " + reviewDto);
        logger.info("Review successfully saved: -> " + saveReview);
        return new ResponseEntity<>(saveReview, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(ReviewDto reviewDto) {
        boolean isExists = reviewDao.existsById(reviewDto.getId());
        if (!isExists){
            logger.error("Review not found from database: -> " + reviewDto);
            return new ResponseEntity<>(Message.of("Review not found"), HttpStatus.NOT_FOUND);
        }else {
            Review review = reviewMapper.toEntity(reviewDto);
            Review updatedReview = reviewDao.save(review);
            if (updatedReview == null) logger.error("Failed while updating review: -> " + reviewDto);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        review.setActive(false);
        ResponseEntity<?> deletedReview = update(reviewMapper.toDto(review));
        if (deletedReview.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Review successfully deleted: -> " + deletedReview);
            return new ResponseEntity<>(deletedReview, HttpStatus.OK);
        }else {
            logger.error("Failed while deleting review: -> " + reviewDto);
            return new ResponseEntity<>(Message.of("Review not deleted"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<ReviewDto> findAllByHotelAndActive(Long hotelId) {
        HotelDto hotelDto = hotelService.findById(hotelId);
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        List<Review> review = reviewDao.findByActiveTrueAndHotel(hotel);
        if (review == null) logger.error("find all by hotel and active not found from database: -> "+ hotelDto);
        logger.info("Review successfully get all reviews by hotel: -> " + review);
        return reviewMapper.toDtoList(review);
    }
}
