package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomCategoryDao extends JpaRepository<RoomCategory, Long> {

}
