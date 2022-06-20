package com.example.bookinghotel.models.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "tb_price")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    float price;
    LocalDate startDate;
    LocalDate endDate;
    boolean active;
    @ManyToOne
    @JoinColumn(name = "room_id")
    Room room;

}
