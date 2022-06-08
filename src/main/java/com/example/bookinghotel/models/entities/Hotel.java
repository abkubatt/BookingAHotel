package com.example.bookinghotel.models.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "hotel")
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
    List<String> photos;
    String phone;
    byte currentScore;
    String email;
    @ManyToOne
    @JoinColumn(name = "city_id")
    City city;
    boolean active;

}

