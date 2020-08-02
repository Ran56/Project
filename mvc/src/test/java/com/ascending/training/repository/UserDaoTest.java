package com.ascending.training.repository;

import com.ascending.training.ApplicationBootstrap;
import com.ascending.training.model.Role;
import com.ascending.training.model.User;
import com.ascending.training.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    private Role role1;
    private Role role2;
    private User user;

    @Before
    public void setUp()
    {

         user = new User();
         user.setName("June");
         user.setEmail("rzhang56@gwu.edu");
         userDao.save(user);

         role1 = new Role();
         role1.setName("Supporter");
         role1.setAllowedResource("Data");
         role1.setAllowedCreate(false);
         role1.setAllowedDelete(false);
         role1.setAllowedRead(true);
         role1.setAllowedUpdate(false);
         roleDao.save(role1);

         role2 = new Role();
         role2.setName("Debugger");
         role2.setAllowedResource("Code");
         role2.setAllowedCreate(true);
         role2.setAllowedDelete(true);
         role2.setAllowedRead(true);
         role2.setAllowedUpdate(true);
         roleDao.save(role2);

         user.addRole(role1);
         user.addRole(role2);
         userDao.update(user);

    }

    @After
    public void tearDown()
    {
        roleDao.delete(role1);
        roleDao.delete(role2);
        userDao.delete(user);

    }

    @Test
    public void getUserTest()
    {
        User user1 = userDao.getUserByName("June");
        Assert.assertEquals(user1.getRoles().size(),2);

    }




}
