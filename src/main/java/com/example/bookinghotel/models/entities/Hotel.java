package com.example.bookinghotel.models.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

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
    String photos;
    String phone;
    String email;
    @ManyToOne
    @JoinColumn(name = "city_id")
    City city;
    boolean active;
}

