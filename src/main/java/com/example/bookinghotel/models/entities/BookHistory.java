package com.example.bookinghotel.models.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "book_history")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    Booking booking;
    LocalDate changeDate;
    String comment;
    boolean active;
}
