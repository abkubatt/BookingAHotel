package com.example.bookinghotel.dao.enumDao;

import com.example.bookinghotel.models.entities.enumentities.StatusOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusOfUserDao extends JpaRepository<StatusOfUser, Long> {
}
