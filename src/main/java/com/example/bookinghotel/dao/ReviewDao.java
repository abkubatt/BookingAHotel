package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDao extends JpaRepository<Review, Long> {

    List<Review> findByActiveTrueAndHotel(Hotel hotel);
}
