package com.example.bookinghotel.models.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "tb_photo")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String link;
    int position;

}
