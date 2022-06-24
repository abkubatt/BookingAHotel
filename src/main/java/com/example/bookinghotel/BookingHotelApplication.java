package com.example.bookinghotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients("com.example.bookinghotel.microservices")
@SpringBootApplication
public class BookingHotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingHotelApplication.class, args);
    }

}
