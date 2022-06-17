package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.City;
import com.example.bookinghotel.models.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelDao extends JpaRepository<Hotel, Long> {

    @Query(value = "select * from hotels h where h.city_id = ?1 ORDER BY h.current_score DESC", nativeQuery = true)
    List<Hotel> findAllByCity(Long cityId);

//    SELECT
//    customer.customer_id,
//    first_name,
//    last_name,
//    amount,
//    payment_date
//            FROM
//    customer
//    INNER JOIN payment
//    ON payment.customer_id = customer.customer_id
//    ORDER BY payment_date;
}
