package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.ReviewDao;
import com.example.bookinghotel.mappers.HotelMapper;
import com.example.bookinghotel.mappers.ReviewMapper;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.ReviewDto;
import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.Review;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;
    private final ReviewMapper reviewMapper = ReviewMapper.INSTANCE;
    private HotelMapper hotelMapper = HotelMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        review.setActive(true);
        Review saveReview = reviewDao.save(review);
        return new ResponseEntity<>(saveReview, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(ReviewDto reviewDto) {
        boolean isExists = reviewDao.existsById(reviewDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("Review not found"), HttpStatus.NOT_FOUND);
        }else {
            Review review = reviewMapper.toEntity(reviewDto);
            Review updatedReview = reviewDao.save(review);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        review.setActive(false);
        ResponseEntity<?> deletedReview = update(reviewMapper.toDto(review));
        if (deletedReview.getStatusCode().equals(HttpStatus.OK)){
            return new ResponseEntity<>(deletedReview, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(Message.of("Review not deleted"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<ReviewDto> findAllByHotelAndActive(HotelDto hotelDto) {
        Hotel hotel = hotelMapper.toEntity(hotelDto);
        return reviewMapper.toDtoList(reviewDao.findByActiveTrueAndHotel(hotel));
    }
}
