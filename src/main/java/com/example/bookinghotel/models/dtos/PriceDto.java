package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.Room;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceDto {
    Long id;
    float price;
    LocalDate startDate;
    LocalDate endDate;
    RoomDto room;
    boolean active;
}
