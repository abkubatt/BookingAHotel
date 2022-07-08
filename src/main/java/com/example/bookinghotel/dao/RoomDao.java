package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.Room;
import com.example.bookinghotel.models.enums.EBedType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDao extends JpaRepository<Room, Long> {
    Room findByHotel(Hotel hotel);
    //@Query(value = "select * from tb_room r where r.hotel_id ")
//    List<Room> findAllByActiveTrueAndHotelAndBedTypeAndCapacity(Hotel hotel, EBedType bedType,int capacity);
//
    @Query("select r from Room r where r.hotel = ?1 and r.bedType = ?2 and r.capacity >= ?3")
    List<Room> findByActiveTrueAndHotelAndBedTypeAndCapacity(Hotel hotel,EBedType bedType, int capacity);
}
