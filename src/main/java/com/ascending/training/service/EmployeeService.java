package com.ascending.training.service;

import com.ascending.training.model.DepartmentHQL;
import com.ascending.training.model.EmployeeHQL;
import com.ascending.training.repository.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public EmployeeHQL save(EmployeeHQL employeeHQL){ return employeeDao.save(employeeHQL); }

    public List<EmployeeHQL> getEmployees(){return employeeDao.getEmployees(); }

    public EmployeeHQL getBy(Long id){ return employeeDao.getBy(id); }

    public boolean delete(EmployeeHQL employeeHQL){ return employeeDao.delete(employeeHQL); }

    public EmployeeHQL update(EmployeeHQL employeeHQL){return employeeDao.update(employeeHQL); }

    public boolean delete(String employeeName){return employeeDao.delete(employeeName); }

    public List<EmployeeHQL> getEmployeesEager(){return employeeDao.getEmployeesEager(); }

    public EmployeeHQL getEmployeeEagerBy(Long id){return employeeDao.getEmployeeEagerBy(id); }

    public EmployeeHQL getEmployeeByName(String employeeName){return employeeDao.getEmployeeByName(employeeName); }



    public EmployeeHQL getEmployeesAndAccountsBy(String employeeName){return employeeDao.getDepartmentAndEmployeesBy(employeeName);}

    public List<Object[]> getDepartmentAndEmployeesAndAccounts(String employeeName){return employeeDao.getDepartmentAndEmployeesAndAccounts(employeeName); }





    public EmployeeHQL getByDepartment(DepartmentHQL departmentHQL){return employeeDao.getByDepartment(departmentHQL); }



}
