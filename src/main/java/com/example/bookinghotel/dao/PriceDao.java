package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceDao extends JpaRepository<Price, Long> {
}
