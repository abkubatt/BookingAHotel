package com.example.bookinghotel.models.dtos;

import com.example.bookinghotel.models.entities.Room;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
public class PriceDto {
    Long id;
    float price;
    LocalDate startDate;
    LocalDate endDate;
    Room room;
    boolean active;
}
