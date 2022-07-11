package com.example.bookinghotel.controllers;

import com.example.bookinghotel.models.dtos.BookingDto;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.ReviewDto;
import com.example.bookinghotel.models.entities.Hotel;
import com.example.bookinghotel.models.enums.EBedType;
import com.example.bookinghotel.models.request.ToCancelBooking;
import com.example.bookinghotel.models.request.ToFiler;
import com.example.bookinghotel.models.request.ToSaveBooking;
import com.example.bookinghotel.models.response.HotelFilterResponse;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.BookingService;
import com.example.bookinghotel.services.CityService;
import com.example.bookinghotel.services.HotelService;
import com.example.bookinghotel.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/guest")
public class GuestController {
    @Autowired private BookingService bookingService;
    @Autowired private ReviewService reviewService;
    @Autowired private CityService cityService;
    @Autowired
    private HotelService hotelService;

    @PostMapping("saveBooking")
    //@PreAuthorize("hasRole('MANAGER') or hasRole('GUEST')")
    public ResponseEntity<?> saveBooking(@RequestBody ToSaveBooking saveBooking){
        return bookingService.saveBooking(saveBooking);}
    @PutMapping("cancelBooking")
    // @PreAuthorize("hasRole('MANAGER') or hasRole('GUEST')")
    public ResponseEntity<?> cancelBooking(@RequestBody ToCancelBooking cancelBooking){
        return bookingService.cancelBooking(cancelBooking);}


    @GetMapping("/findAllCity")
    public ResponseEntity<?> findAllCities(){
        return cityService.findAll();}
    @GetMapping("/findAllHotelsByCity")
    public ResponseEntity<?> findAllHotelsByCity(Long cityId){
        List<HotelDto> hotels = hotelService.findAllHotelsByCity(cityId);
        if (hotels.isEmpty()){
            return new ResponseEntity<>(Message.of("Hotel not found in this city: -> " + cityId) ,HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(hotels,HttpStatus.OK);
        }}


    @PostMapping("/saveReview")
    public ResponseEntity<?> saveReview(@RequestBody ReviewDto reviewDto){
        return reviewService.save(reviewDto);
    }
    @PutMapping("/updateReview")
    public ResponseEntity<?> updateReview(@RequestBody ReviewDto reviewDto){
        return reviewService.update(reviewDto);
    }
    @PutMapping("/deleteReview")
    public ResponseEntity<?> deleteReview(@RequestParam Long reviewId){
        return reviewService.delete(reviewId);
    }



    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestParam Long cityId ,
                                    @RequestParam
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate checkInDate,
                                    @RequestParam
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
                                    @RequestParam int guestAmount,
                                    @RequestParam EBedType bedType){
        return hotelService.filter(cityId,checkInDate,checkOutDate,bedType,guestAmount);
    }

    @PutMapping("/getByRating")
    public ResponseEntity<?> Rating(@RequestBody List<HotelFilterResponse> hotels){
        return hotelService.Rating(hotels);
    }




}
