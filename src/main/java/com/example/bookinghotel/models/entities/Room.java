package com.example.bookinghotel.models.entities;
import com.example.bookinghotel.models.entities.enumentities.TypeOfRoom;
import com.example.bookinghotel.models.enums.ETypeOfRoom;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Table(name = "room")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    int capacity;
    String bedType;
    float square;
    boolean wifi;
    String view;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    Hotel hotel;
    @OneToOne
    @JoinColumn(name = "type_of_room")
    TypeOfRoom typeOfRoom;

}
