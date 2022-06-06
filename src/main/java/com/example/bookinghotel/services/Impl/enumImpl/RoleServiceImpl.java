package com.example.bookinghotel.services.Impl.enumImpl;

import com.example.bookinghotel.dao.enumDao.RoleDao;
import com.example.bookinghotel.mappers.enumMappers.RoleMapper;
import com.example.bookinghotel.models.dtos.enumdtos.RoleDto;
import com.example.bookinghotel.models.entities.enumentities.Role;
import com.example.bookinghotel.models.response.Message;
import com.example.bookinghotel.services.enumService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    private final RoleMapper roleMapper = RoleMapper.INSTANCE;

    @Override
    public ResponseEntity<?> save(RoleDto roleDto) {
        Role role = roleMapper.toEntity(roleDto);
        Role saveRole = roleDao.save(role);
        return new ResponseEntity<>(Message.of("Role saved"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(RoleDto roleDto) {
        boolean isExists = roleDao.existsById(roleDto.getId());
        if (!isExists){
            return new ResponseEntity<>(Message.of("Role is not found"), HttpStatus.OK);
        }else{
            Role role = roleMapper.toEntity(roleDto);
            Role updatedRole = roleDao.save(role);
            return new ResponseEntity<>(Message.of("Role updated"), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> delete(RoleDto roleDto) {
        roleDao.deleteById(roleDto.getId());
        return new ResponseEntity<>(Message.of("Role deleted"), HttpStatus.OK);
    }


}
