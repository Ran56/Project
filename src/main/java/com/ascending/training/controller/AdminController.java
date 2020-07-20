package com.ascending.training.controller;


import com.ascending.training.model.Role;
import com.ascending.training.model.User;
import com.ascending.training.service.RoleService;
import com.ascending.training.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController   // Create,delete role and promote user's authorization
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(getClass());


    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<Role> create(@RequestBody Role role)
    {
       return new ResponseEntity<>(roleService.save(role), HttpStatus.OK);//startsWith()有问题
    }


    @RequestMapping(value = "",method = RequestMethod.DELETE)
    public void delete(@RequestParam(name = "name") String name)
    {
        Role role = roleService.getRoleByName(name);
        roleService.delete(role);
    }


    @RequestMapping(value = "",method = RequestMethod.PATCH)
    public ResponseEntity<User> promoteOrDemote(@RequestParam("userName") String name, @RequestParam("roleName") String roleName)
    {
        User user = userService.getUserByName(name);
        if(user==null)
        {
            logger.error("Cannot find user");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Role role1 = roleService.getRoleByName(roleName);
        if(role1==null)
        {
            logger.error("Cannot find role");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        user.addRole(role1);
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }


    




}
