package com.ascending.training.service;


import com.ascending.training.model.DepartmentHQL;
import com.ascending.training.repository.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;


    public DepartmentHQL save(DepartmentHQL department)
    {
        return departmentDao.save(department);
    }

    public DepartmentHQL update(DepartmentHQL department)
    {
        return departmentDao.update(department);
    }

    public boolean delete(String deptName)
    {
        return departmentDao.delete(deptName);
    }

    public DepartmentHQL getBy(Long id)
    {
        return departmentDao.getBy(id);
    }


    public List<DepartmentHQL> getDepartments(){ return departmentDao.getDepartments(); }

    public boolean delete(DepartmentHQL dep){ return departmentDao.delete(dep); }

    public List<DepartmentHQL> getDepartmentsEager(){ return departmentDao.getDepartmentsEager(); }

    public DepartmentHQL getDepartmentEagerBy(Long id) {return departmentDao.getDepartmentEagerBy(id); }

    public DepartmentHQL getDepartmentByName(String deptName) {return departmentDao.getDepartmentByName(deptName); }

    public DepartmentHQL getDepartmentAndEmployeesBy(String deptName){return departmentDao.getDepartmentAndEmployeesBy(deptName);}

    public List<Object[]> getDepartmentAndEmployeesAndAccounts(String deptName){return departmentDao.getDepartmentAndEmployeesAndAccounts(deptName); }



}
