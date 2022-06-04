package com.example.bookinghotel.dao.enumDao;

import com.example.bookinghotel.models.entities.enumentities.TypeOfRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfRoomDao extends JpaRepository<TypeOfRoom, Long> {
}
