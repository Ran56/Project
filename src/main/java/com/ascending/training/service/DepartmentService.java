package com.ascending.training.service;


import com.ascending.training.model.Department;
import com.ascending.training.repository.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;


    public Department save(Department department)
    {
        department.setName("HR3");
        return departmentDao.save(department);
    }

    public Department update(Department department)
    {
        return departmentDao.update(department);
    }

    public boolean delete(String deptName)
    {
        return departmentDao.delete(deptName);
    }

    public Department getBy(Long id)
    {
        return departmentDao.getBy(id);
    }

    public List<Department> getDepartments(){ return departmentDao.getDepartments(); }

    public boolean delete(Department dep){ return departmentDao.delete(dep); }

    public List<Department> getDepartmentsEager(){ return departmentDao.getDepartmentsEager(); }

    public Department getDepartmentEagerBy(Long id) {return departmentDao.getDepartmentEagerBy(id); }

    public Department getDepartmentByName(String deptName) {return departmentDao.getDepartmentByName(deptName); }

    public Department getDepartmentAndEmployeesBy(String deptName){return departmentDao.getDepartmentAndEmployeesBy(deptName);}

    public List<Object[]> getDepartmentAndEmployeesAndAccounts(String deptName){return departmentDao.getDepartmentAndEmployeesAndAccounts(deptName); }



}
