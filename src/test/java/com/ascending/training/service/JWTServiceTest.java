package com.ascending.training.service;

import com.ascending.training.ApplicationBootstrap;
import com.ascending.training.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class JWTServiceTest {
    @Autowired
    private  JWTService jwtService;
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Test
    public void generateTokenTest()
    {
        User u = new User();
        u.setId(10);
        u.setName("RyanZ");
        String token = jwtService.generateToken(u);
        logger.debug("hello world");
        Assert.assertNotNull(token);
    }
}
