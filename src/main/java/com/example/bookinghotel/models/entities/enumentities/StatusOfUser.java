package com.example.bookinghotel.models.entities.enumentities;

import com.example.bookinghotel.models.enums.ERole;
import com.example.bookinghotel.models.enums.EStatusOfUser;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Table(name = "status_of_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusOfUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Enumerated(EnumType.STRING)
    EStatusOfUser statusOfUser;
}