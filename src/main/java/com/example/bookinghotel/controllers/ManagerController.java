package com.example.bookinghotel.controllers;

import com.example.bookinghotel.dao.ReplyToReviewDao;
import com.example.bookinghotel.models.dtos.*;
import com.example.bookinghotel.models.request.*;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/manager")
public class ManagerController {
    @Autowired private HotelService hotelService;
    @Autowired private BookingService bookingService;
    @Autowired private ReplyToReviewService replyToReviewService;
    @Autowired private RoomService roomService;
    @Autowired private PriceService priceService;
    @Autowired private FileService fileService;
    @Autowired private PhotoService photoService;
    @Autowired private CityService cityService;
    @Autowired private ReviewService reviewService;
    @Autowired private RoomCategoryService roomCategoryService;

    @PostMapping("saveBooking")
    //@PreAuthorize("hasRole('MANAGER') or hasRole('GUEST')")
    public ResponseEntity<?> saveBooking(@RequestBody ToSaveBooking saveBooking){
        return bookingService.saveBooking(saveBooking);
    }
    @PutMapping("cancelBooking")
   // @PreAuthorize("hasRole('MANAGER') or hasRole('GUEST')")
    public ResponseEntity<?> cancelBooking(@RequestBody ToCancelBooking cancelBooking){
        return bookingService.cancelBooking(cancelBooking);
    }



    @PostMapping("/saveRoom")
    public ResponseEntity<?> saveRoom(@RequestBody ToSaveRoom saveRoom){
        return roomService.saveRoom(saveRoom);
    }
    @PutMapping("/updateRoom")
    public ResponseEntity<?> updateRoom(@RequestBody RoomDto roomDto){
        return roomService.update(roomDto);
    }
    @PutMapping("/deleteRoom")
    public ResponseEntity<?> deleteRoom(@RequestParam Long roomId){
        return roomService.delete(roomId);
    }



    @PutMapping("/updatePrice")
    public ResponseEntity<?> updatePrice(@RequestBody PriceDto priceDto){
        return priceService.update(priceDto);
    }
    @PutMapping("/deletePrice")
    public ResponseEntity<?> deletePrice(@RequestParam Long priceId){
        return priceService.delete(priceId);
    }



    @PutMapping("/updatePhoto")
    public ResponseEntity<?> updatePhoto(@RequestBody PhotoDto photoDto){
        return photoService.updatePhoto(photoDto);
    }
    @DeleteMapping("/deletePhoto")
    public ResponseEntity<?> deletePhoto(@RequestParam Long photoId){
        return photoService.deletePhoto(photoId);
    }
    @PostMapping("/savePhotoToHotel")
    public ResponseEntity<?> savePhotoToHotel(@RequestParam MultipartFile file, @RequestParam Long hotelId, @RequestParam int position){
        return fileService.uploadImageToHotel(file,hotelId,position);}

    @PostMapping("/saveReplyToReview")
    public ResponseEntity<?> saveReplyToReview(@RequestBody ReplyToReviewDto replyToReviewDto){
        return replyToReviewService.save(replyToReviewDto);
    }
    @PutMapping("/deleteReview")
    public ResponseEntity<?> deleteReview(@RequestParam Long reviewId){
        return reviewService.delete(reviewId);
    }
    @PostMapping("/deleteReplyToReview")
    public ResponseEntity<?> deleteReplyToReview(@RequestParam Long id){
        return replyToReviewService.delete(id);
    }



    @PutMapping("update")
    public ResponseEntity<?> updateHotel(@RequestBody HotelDto hotelDto){
        return hotelService.update(hotelDto);}


    @GetMapping("/findAllCity")
    public ResponseEntity<?> findAllCities(){
        return cityService.findAll();
    }

    @PostMapping("/saveCategoryAndPrice")
    public ResponseEntity<?> saveCategoryAndPrice(@RequestBody ToSaveCategoryAndPrice toSaveCategoryAndPrice){
        return roomCategoryService.saveCategoryAndPrice(toSaveCategoryAndPrice);
    }

    @PutMapping("/findAllReviewByHote")
    public ResponseEntity<?> findAllReviewByHotel(@RequestParam Long hotelId){
        List<ReviewDto> reviews = reviewService.findAllByHotelAndActive(hotelId);
        if (reviews.isEmpty()){
            return new ResponseEntity<>(Message.of("Reviews not found by Hotel"), HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.ok(reviews);
        }
    }

}
