package com.example.bookinghotel.mappers;

import com.example.bookinghotel.models.dtos.BookHistoryDto;
import com.example.bookinghotel.models.entities.BookHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookHistoryMapper extends BaseMapper<BookHistory, BookHistoryDto> {
    BookHistoryMapper INSTANCE = Mappers.getMapper(BookHistoryMapper.class);
}
