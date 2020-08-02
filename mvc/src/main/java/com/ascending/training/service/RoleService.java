package com.ascending.training.service;

import com.ascending.training.model.Role;
import com.ascending.training.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role getRoleByName(String name)
    {
        return roleDao.getRoleByName(name);
    }

    public List<Role> getAllRoles()
    {
        return roleDao.findAllRoles();
    }

    public void delete(Role role){ roleDao.delete(role);}

    public Role save(Role role) { return roleDao.save(role);}

    public Role update(Role role) { return roleDao.update(role);}

    public Role getById(Long id){ return roleDao.findById(id);}

}
