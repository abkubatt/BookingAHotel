package com.example.bookinghotel.services.Impl;

import com.example.bookinghotel.dao.PhotoDao;
import com.example.bookinghotel.mappers.PhotoMapper;
import com.example.bookinghotel.models.dtos.PhotoDto;
import com.example.bookinghotel.models.entities.City;
import com.example.bookinghotel.models.entities.Photo;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;
    private final PhotoMapper photoMapper = PhotoMapper.INSTANCE;
    @Override
    public PhotoDto save(PhotoDto photoDto) {
        Photo photo = photoMapper.toEntity(photoDto);
        Photo savedPhoto = photoDao.save(photo);
        return photoMapper.toDto(photo);
    }


    @Override
    public ResponseEntity<?> savePhoto(PhotoDto photoDto) {
        Photo photo = photoMapper.toEntity(photoDto);
        Photo savedPhoto = photoDao.save(photo);
        return new ResponseEntity<>(savedPhoto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updatePhoto(PhotoDto photoDto) {
        boolean isExists = photoDao.existsById(photoDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("Photo not found"),HttpStatus.NOT_FOUND);
        }else{
            Photo photo = photoMapper.toEntity(photoDto);
            Photo updatedPhoto = photoDao.save(photo);
            return new ResponseEntity<>(updatedPhoto, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> deletePhoto(Long photoId) {
        photoDao.deleteById(photoId);
        return new ResponseEntity<>(Message.of("Photo deleted"), HttpStatus.OK);
    }
}
