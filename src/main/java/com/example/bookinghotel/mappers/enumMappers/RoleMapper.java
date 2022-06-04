package com.example.bookinghotel.mappers.enumMappers;

import com.example.bookinghotel.mappers.BaseMapper;
import com.example.bookinghotel.models.dtos.enumdtos.RoleDto;
import com.example.bookinghotel.models.entities.enumentities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper extends BaseMapper<Role, RoleDto> {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
}
