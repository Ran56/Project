package com.ascending.training.service;

import com.ascending.training.model.User;
import com.ascending.training.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers()
    {
       return userDao.findAllUsers();
    }

    public User getUserByCredentials(String email, String password)
    {
        return userDao.getUserByCredentials(email,password);
    }





}
