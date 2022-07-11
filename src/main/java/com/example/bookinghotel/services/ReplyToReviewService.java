package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.ReplyToReviewDto;
import com.example.bookinghotel.models.entities.ReplyToReview;
import org.springframework.http.ResponseEntity;

public interface ReplyToReviewService {
    ResponseEntity<?> save(ReplyToReviewDto replyToReviewDto);
    ResponseEntity<?> delete(Long id);

    ReplyToReviewDto findById(Long id);


}
