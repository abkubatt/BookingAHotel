package com.example.bookinghotel.dao.enumDao;

import com.example.bookinghotel.models.entities.enumentities.StatusOfBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusOfBookDao extends JpaRepository<StatusOfBook, Long> {
}
