package com.ascending.training.repository;

import com.ascending.training.model.Role;
import com.ascending.training.model.User;

import java.util.List;

public interface RoleDao {

    Role getRoleByName(String name);
    List<Role> findAllRoles();
    Role findById(Long id);
    Role save(Role role);
    void delete(Role role);
    Role update(Role role);
}
