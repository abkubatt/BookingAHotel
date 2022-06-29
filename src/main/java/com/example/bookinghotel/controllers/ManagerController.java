package com.example.bookinghotel.controllers;

import com.example.bookinghotel.models.dtos.*;
import com.example.bookinghotel.models.request.*;
import com.example.bookinghotel.services.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/manager")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerController {
    @Autowired HotelService hotelService;
    @Autowired BookingService bookingService;

    @Autowired
    ReplyToReviewService replyToReviewService;
    @Autowired
    RoomCategoryService roomCategoryService;
    @Autowired
    RoomService roomService;
    @Autowired
    PriceService priceService;
    @Autowired
    FileService fileService;
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

    @PostMapping("/uploadPhotoToHotel")
    public ResponseEntity<?> uploadPhotoToHotel(@RequestParam MultipartFile file, @RequestParam Long hotelId, @RequestParam int position){
        return fileService.uploadImageToHotel(file,hotelId,position);
    }
    @PutMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody ToFiler filer){
        return hotelService.filter(filer);
    }
    @PostMapping("/saveCategory")
    public ResponseEntity<?> saveCategory(@RequestBody ToSaveCategoryAndPrice saveCategoryAndPrice){
        return roomCategoryService.saveCategoryAndPrice(saveCategoryAndPrice);
    }
}
