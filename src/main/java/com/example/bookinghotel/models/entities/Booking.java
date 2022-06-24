package com.example.bookinghotel.models.entities;

import com.example.bookinghotel.models.enums.EStatusBooking;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "tb_booking")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "hotel_id")
    Hotel hotel;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "room_id")
    Room room;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "guest_id")
    User guest;
    String comment;
    float priceOfBook;
    @Enumerated(EnumType.STRING)
    EStatusBooking statusBooking;

}
