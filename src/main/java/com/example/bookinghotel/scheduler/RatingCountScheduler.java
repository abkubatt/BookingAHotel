package com.example.bookinghotel.scheduler;

import com.example.bookinghotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RatingCountScheduler {
    @Autowired
    private HotelService hotelService;
    //    @Scheduled(fixedDelay = 1000)
//    public void scheduleFixedDelayTask() throws InterruptedException {
//        Thread.sleep(3000);
//        System.out.println(
//                "Fixed delay task - " + System.currentTimeMillis() / 1000);
//    }
//
//    @Scheduled(fixedRate = 1000)
//    public void scheduleFixedRateTask() throws InterruptedException {
//        Thread.sleep(3000);
//        System.out.println(
//                "Fixed rate task - " + System.currentTimeMillis() / 1000);
//    }

    @Scheduled(cron = "0 0 2 * * *")
    public void scheduleCronTask() {
        hotelService.countCurrentScore();
    }
}
