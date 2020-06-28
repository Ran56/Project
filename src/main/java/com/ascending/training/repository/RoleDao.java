package com.ascending.training.repository;

import com.ascending.training.model.Role;

import java.util.List;

public interface RoleDao {

    Role getRoleByName(String name);
    List<Role> findAllRoles();



}
