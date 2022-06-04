package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDao extends JpaRepository<Booking, Long> {

}
