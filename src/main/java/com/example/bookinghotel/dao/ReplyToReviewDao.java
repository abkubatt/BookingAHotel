package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.ReplyToReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyToReviewDao extends JpaRepository<ReplyToReview, Long> {
}
