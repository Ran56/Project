package com.ascending.training.service;

import com.ascending.training.model.Employee;
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

    public User getUserByCredentials(String email, String password) { return userDao.getUserByCredentials(email,password); }

    public User save(User user){ return userDao.save(user); }

    public User getById(Long id)
   {
       return userDao.findById(id);
   }

    public User update(User user){ return userDao.update(user); }

    public User getUserByName(String name){ return userDao.getUserByName(name); }


}
