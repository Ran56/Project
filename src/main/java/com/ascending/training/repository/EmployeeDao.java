package com.ascending.training.repository;

import com.ascending.training.model.Department;
import com.ascending.training.model.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee save(Employee employee);
    List<Employee> getEmployees();
    Employee getBy(Long id);
    boolean delete(Employee employee);

    Employee update(Employee employee);
    boolean delete(String employeeName);
    List<Employee> getEmployeesEager();
    Employee getEmployeeEagerBy(Long id);
    Employee getEmployeeByName(String employeeName);




    Employee getDepartmentAndEmployeesBy(String employeeName);
    List<Object[]> getDepartmentAndEmployeesAndAccounts(String employeeName);

    Employee getByDepartment(Department department);

}
