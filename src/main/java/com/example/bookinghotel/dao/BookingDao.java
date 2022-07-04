package com.example.bookinghotel.dao;

import com.example.bookinghotel.models.entities.Booking;
import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.entities.Room;
import com.example.bookinghotel.models.enums.EBedType;
import com.example.bookinghotel.models.enums.EStatusBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BookingDao extends JpaRepository<Booking, Long> {
    @Query(value = "select * from tb_booking b where DATE(b.check_in_date) <= :checkInDate and DATE(b.check_out_date) >= :checkOutDate",nativeQuery = true)
    List<Booking> findAll(@Param("checkInDate") Date checkInDate, @Param("checkOutDate") Date checkOutDate);

    @Query(value = "select * from tb_booking b where b.hotel_id = ?1",nativeQuery = true)
    List<Booking> findAllByHotel(Long hotelId);
//    @Query(value = "select * from tb_booking b where b.check_in_date = :checkInDate or b.check_out_date = :checkOutDate or b.check_in_date between :checkInDate and :checkOutDate and b.check_out_date between :checkInDate and :checkOutDate",nativeQuery = true)
//    List<Booking> findAllBooking(@Param("checkInDate") LocalDate checkInDate, @Param("checkOutDate") LocalDate checkOutDate);

    @Query(value = "select * from tb_booking b inner join tb_room r on r.id = b.room_id where b.hotel_id = :hotelId and r.capacity >= :capacityPerson and b.check_in_date = :checkInDate or b.check_out_date = :checkOutDate or b.check_in_date between :checkInDate and :checkOutDate and b.check_out_date between :checkInDate and :checkOutDate",nativeQuery = true)
    List<Booking> findAllBooking(@Param("hotelId") Long hotelId, @Param("capacityPerson") int capacityPerson,@Param("checkInDate") LocalDate checkInDate, @Param("checkOutDate") LocalDate checkOutDate);




    List<Booking> findAllByRoomAndStatusBooking(Room room, EStatusBooking statusBooking);


//    @Query(value = "select * from tb_booking b inner join tb_room r on r.id = b.room_id where r.capacity >= :capacityPerson and b.check_out_date between :checkInDate and :checkOutDate",nativeQuery = true)
//    List<Booking> findAllBooking(@Param("capacityPerson") int capacityPerson,@Param("checkInDate") LocalDate checkInDate, @Param("checkOutDate") LocalDate checkOutDate);

}









