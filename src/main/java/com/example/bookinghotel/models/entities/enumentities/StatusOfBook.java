package com.example.bookinghotel.models.entities.enumentities;

import com.example.bookinghotel.models.enums.ERole;
import com.example.bookinghotel.models.enums.EStatusOfBook;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Table(name = "status_of_book")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusOfBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Enumerated(EnumType.STRING)
    EStatusOfBook statusOfBook;
}