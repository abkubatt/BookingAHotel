package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.Price;
import com.example.bookinghotel.models.entities.RoomCategory;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PriceDao extends JpaRepository<Price, Long> {

    @Query(value = "select * from tb_price p where p.room_category_id = ?1 and p.start_date <= ?2 and p.end_date >= ?2",nativeQuery = true)
    Price findByRoomCategoryAndStartDateAndEndDate(Long roomCategoryId, LocalDate nowDate);
}
