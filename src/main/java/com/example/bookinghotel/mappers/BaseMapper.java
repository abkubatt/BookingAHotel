package com.example.bookinghotel.mappers;

import java.util.List;

public interface BaseMapper <Entity, Dto>{
    Entity toEntity(Dto dto);
    Dto toDto(Entity entity);
    List<Entity> toEntityList(List<Dto> dtoList);
    List<Dto> toDtoList(List<Entity> entityList);
}
