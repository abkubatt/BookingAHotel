package com.example.bookinghotel.controllers;

import com.example.bookinghotel.models.dtos.*;
import com.example.bookinghotel.models.request.ToCancelBooking;
import com.example.bookinghotel.models.request.ToSaveBooking;
import com.example.bookinghotel.models.request.ToSaveRoom;
import com.example.bookinghotel.services.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/manager")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerController {
    @Autowired HotelService hotelService;
    @Autowired BookingService bookingService;

    @Autowired
    ReplyToReviewService replyToReviewService;
    @Autowired
    RoomService roomService;
    @Autowired
    PriceService priceService;
    @Autowired
    PhotoService photoService;

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
    @PostMapping("/saveReplyToReview")
    public ResponseEntity<?> saveReplyToReview(@RequestBody ReplyToReviewDto replyToReviewDto){
        return replyToReviewService.save(replyToReviewDto);
    }

    @PutMapping("/updateRoom")
    public ResponseEntity<?> updateRoom(@RequestBody RoomDto roomDto){
        return roomService.update(roomDto);
    }
    @PutMapping("/deleteRoom")
    public ResponseEntity<?> deleteRoom(@RequestBody RoomDto roomDto){
        return roomService.delete(roomDto);
    }


    @PutMapping("/updatePrice")
    public ResponseEntity<?> updatePrice(@RequestBody PriceDto priceDto){
        return priceService.update(priceDto);
    }
    @PutMapping("/deletePrice")
    public ResponseEntity<?> deletePrice(@RequestBody PriceDto priceDto){
        return priceService.delete(priceDto);
    }
    @PostMapping("/savePhoto")
    public ResponseEntity<?> savePhoto(@RequestBody PhotoDto photoDto){
        return photoService.savePhoto(photoDto);
    }
    @PutMapping("/updatePhoto")
    public ResponseEntity<?> updatePhoto(@RequestBody PhotoDto photoDto){
        return photoService.updatePhoto(photoDto);
    }
    @DeleteMapping("/deletePhoto")
    public ResponseEntity<?> deletePhoto(@RequestParam Long photoId){
        return photoService.deletePhoto(photoId);
    }

    @PostMapping("/saveRoomRequest")
    public ResponseEntity<?> saveRoomEntity(@RequestBody ToSaveRoom saveRoom){
        return roomService.saveRoom(saveRoom);
    }

}
