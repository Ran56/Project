package com.ascending.training.repository;

import com.ascending.training.model.DepartmentHQL;
import com.ascending.training.model.EmployeeHQL;

import java.util.List;

public interface EmployeeDao {

    EmployeeHQL save(EmployeeHQL employeeHQL);
    List<EmployeeHQL> getEmployees();
    EmployeeHQL getBy(Long id);
    boolean delete(EmployeeHQL employeeHQL);

    EmployeeHQL update(EmployeeHQL employeeHQL);
    boolean delete(String employeeName);
    List<EmployeeHQL> getEmployeesEager();
    EmployeeHQL getEmployeeEagerBy(Long id);
    EmployeeHQL getEmployeeByName(String employeeName);




    EmployeeHQL getDepartmentAndEmployeesBy(String employeeName);
    List<Object[]> getDepartmentAndEmployeesAndAccounts(String employeeName);



}
