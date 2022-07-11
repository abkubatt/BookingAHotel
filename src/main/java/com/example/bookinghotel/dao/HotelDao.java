package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.City;
import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.enums.EBedType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface HotelDao extends JpaRepository<Hotel, Long> {

    @Query(value = "select * from tb_hotel h where h.city_id = ?1 ORDER BY h.current_score DESC", nativeQuery = true)
    List<Hotel> findAllByCity(Long cityId);


  @Query(value = "select * from tb_hotel h inner join tb_room r on r.hotel_id = h.id where h.city_id = ?1 and r.capacity >= ?2 and r.bed_type = :#{#bedtype.name()}", nativeQuery = true)
  List<Hotel> findAll(Long cityId,int capacityPerson, @Param("bedtype") EBedType bedType);


    @Query(value = "select * from tb_hotel h where h.hotel_status = 'ACTIVE'", nativeQuery = true)
    List<Hotel> findAllHotel();

    @Query(value = "select * from tb_hotel h where h.city_id = :id and exists (select * from tb_room r where r.hotel_id = h.id and r.bed_type = :#{#bedType.name()})",nativeQuery = true)
    List<Hotel> findAllByCityAndBedType(@Param("id") Long cityId,  @Param("bedType") EBedType bedType);
}
/*- список всех отелей по городу и по рейтингу

        - фильтр по – городу
        -- дате заезда и дате выезда
        -- кол-во человек
        -- кол-во номеров
        - фильтр по типу кровати


        - получить полную информацию по отелю с доступными вариантами
        номеров
*/