package com.example.bookinghotel.controllers.enumControllers;

import com.example.bookinghotel.models.dtos.enumdtos.RoleDto;
import com.example.bookinghotel.services.enumService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    public RoleDto save(@RequestBody RoleDto roleDto){
        return roleService.save(roleDto);
    }
}
