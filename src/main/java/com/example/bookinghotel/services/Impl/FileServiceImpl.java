package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.microservices.FileServiceFeign;
import com.example.bookinghotel.microservices.json.FileServiceResponse;
import com.example.bookinghotel.models.dtos.HotelDto;
import com.example.bookinghotel.models.dtos.PhotoDto;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.FileService;
import com.example.bookinghotel.services.HotelService;
import com.example.bookinghotel.services.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;
@Service
public class FileServiceImpl implements FileService {
    Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Autowired
    private HotelService hotelService;
    @Autowired
    private FileServiceFeign fileServiceFeign;
    @Autowired
    private PhotoService photoService;
    @Override
    //@Transactional
    public ResponseEntity<?> uploadImageToHotel(MultipartFile file, Long hotelId, int orderNum) {
        HotelDto hotelDto = hotelService.findById(hotelId);
        if (hotelDto != null){
            PhotoDto photoDto = new PhotoDto();
            photoDto.setHotel(hotelDto);
            photoDto.setPosition(orderNum);

            try {
                FileServiceResponse response = fileServiceFeign.upload(file);
                photoDto.setLink(response.getDownloadUri());
                PhotoDto savedPhoto = photoService.save(photoDto);
                logger.info("Photo successfully upload: -> " + savedPhoto);
                return new ResponseEntity<>(savedPhoto, HttpStatus.OK);
            }catch (Exception e){
                logger.error("Error while uploading: exception -> "+e + " " + photoDto);
                return new ResponseEntity<>(Message.of("Failed while uploading photo"), HttpStatus.NOT_ACCEPTABLE);
            }

        }
        logger.error("Method uploadImageToHotel not worked");
        return new ResponseEntity<>(Message.of("Method uploadImageToHotel not worked"), HttpStatus.NOT_ACCEPTABLE);
    }
}
