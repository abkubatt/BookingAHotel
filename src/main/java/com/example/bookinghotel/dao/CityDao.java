package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.City;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends JpaRepository<City, Long> {
}
