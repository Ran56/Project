package com.ascending.training.controller;

import com.ascending.training.model.Role;
import com.ascending.training.model.User;
import com.ascending.training.service.JWTService;
import com.ascending.training.service.RoleService;
import com.ascending.training.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {//登陆然后返回token

    @Autowired
    private UserService userService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private RoleService roleService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    //      /auth POST LOGIN
    @RequestMapping(value = "",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)//一般登陆都是POST
    public ResponseEntity<String> authentication(@RequestBody User user)
    {

        logger.debug("username is "+user.getName() +" password is "+user.getPassword());
        try{
            User u = userService.getUserByCredentials(user.getName(),user.getPassword());
            String token = jwtService.generateToken(u);

            Map<String,String> map = new HashMap<>();//用map存数据在postman中返回json格式的token
            map.put("token",token);
            String json = new ObjectMapper().writeValueAsString(map);
            return new ResponseEntity<>(json, HttpStatus.OK);
        }catch(Exception e)
        {
            e.printStackTrace();
            logger.error("UNAUTHORIZED",e);
            return new ResponseEntity<>("Your username or password is incorrect", HttpStatus.UNAUTHORIZED);
        }
    }

    //      /auth/ POST CREATE
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public User create(@RequestBody User user)
    {
        logger.debug("Creating user ");
        User user1 = userService.save(user);
        Role role = roleService.getRoleByName("Manager");
        user1.addRole(role);
        userService.update(user1);
        return user1;
    }

//
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public List<User> getAllUsers()
//    {
//       return  userService.getAllUsers();
//    }
//


}
