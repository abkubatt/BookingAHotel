package com.example.bookinghotel.services.Impl.enumImpl;

import com.example.bookinghotel.dao.enumDao.RoleDao;
import com.example.bookinghotel.mappers.enumMappers.RoleMapper;
import com.example.bookinghotel.models.dtos.enumdtos.RoleDto;
import com.example.bookinghotel.models.entities.enumentities.Role;
import com.example.bookinghotel.services.enumService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    private final RoleMapper roleMapper = RoleMapper.INSTANCE;

    @Override
    public RoleDto save(RoleDto roleDto) {
        Role role = roleMapper.toEntity(roleDto);
        return roleMapper.toDto(roleDao.save(role));
    }


}
