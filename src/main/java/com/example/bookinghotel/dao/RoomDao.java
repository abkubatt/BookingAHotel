package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDao extends JpaRepository<Room, Long> {
    Room findByHotel(Hotel hotel);
}
