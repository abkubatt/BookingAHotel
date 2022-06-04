package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.BookHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookHistoryDao extends JpaRepository<BookHistory, Long> {

}
