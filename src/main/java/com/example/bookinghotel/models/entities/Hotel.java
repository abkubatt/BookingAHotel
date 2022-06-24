package com.example.bookinghotel.models.entities;

import com.example.bookinghotel.models.enums.EHotelStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "tb_hotel")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String description;
    String address;
    byte star;
//    @OneToMany(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "photo_id")
   // List<Photo> photos;
    String phone;
    double currentScore;
    String email;
    @ManyToOne
    @JoinColumn(name = "city_id")
    City city;
    @Enumerated(EnumType.STRING)
    EHotelStatus hotelStatus;

}

