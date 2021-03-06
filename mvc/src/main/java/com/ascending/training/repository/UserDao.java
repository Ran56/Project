package com.ascending.training.repository;

import com.ascending.training.model.User;

import java.util.List;

public interface UserDao {

    User save(User user);
    User findById(Long id);
    User getUserByEmail(String email);
    User getUserByCredentials(String username,String password);
    void delete(User user);
    List<User> findAllUsers();
    User update(User user);
    User getUserByName(String name);




}
