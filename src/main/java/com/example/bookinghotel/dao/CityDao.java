package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.City;import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityDao extends JpaRepository<City, Long> {
    @Query(value = "select * from tb_city c where c.active = true order by c.name ASC",nativeQuery = true)
    List<City> findAllByName();
}
