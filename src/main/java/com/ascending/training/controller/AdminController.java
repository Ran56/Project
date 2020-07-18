package com.ascending.training.controller;


import com.ascending.training.model.Role;
import com.ascending.training.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Role signUp(@RequestBody Role role)
    {
       return roleService.save(role);
    }





}
