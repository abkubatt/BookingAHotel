package com.example.bookinghotel.services;

import com.example.bookinghotel.models.dtos.ReplyToReviewDto;
import com.example.bookinghotel.models.entities.ReplyToReview;

public interface ReplyToReviewService {
    ReplyToReviewDto save(ReplyToReviewDto replyToReviewDto);
}
