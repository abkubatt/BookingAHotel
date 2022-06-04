package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDao extends JpaRepository<Review, Long> {
}
