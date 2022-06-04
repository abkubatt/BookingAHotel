package com.example.bookinghotel.services.Impl.enumImpl;

import com.example.bookinghotel.dao.enumDao.StatusOfBookDao;
import com.example.bookinghotel.mappers.enumMappers.StatusOfBookMapper;
import com.example.bookinghotel.models.dtos.enumdtos.StatusOfBookDto;
import com.example.bookinghotel.models.entities.enumentities.StatusOfBook;
import com.example.bookinghotel.services.enumService.StatusOfBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusOfBookServiceImpl implements StatusOfBookService {
    @Autowired
    private StatusOfBookDao statusOfBookDao;
    private final StatusOfBookMapper statusOfBookMapper = StatusOfBookMapper.INSTANCE;

    @Override
    public StatusOfBookDto save(StatusOfBookDto statusOfBookDto) {
        StatusOfBook statusOfBook = statusOfBookMapper.toEntity(statusOfBookDto);
        return statusOfBookMapper.toDto(statusOfBookDao.save(statusOfBook));
    }
}
