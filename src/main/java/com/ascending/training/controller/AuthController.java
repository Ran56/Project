package com.ascending.training.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String authentication(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password)
    {
        logger.debug("username is "+username + " password is "+password);
        return null;
    }


}
