package com.example.bookinghotel.models.entities.enumentities;

import com.example.bookinghotel.models.enums.ERole;
import com.example.bookinghotel.models.enums.ETypeOfRoom;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Table(name = "type_of_room")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TypeOfRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Enumerated(EnumType.STRING)
    ETypeOfRoom typeOfRoom;
}