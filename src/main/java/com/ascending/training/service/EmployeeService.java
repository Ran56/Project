package com.ascending.training.service;

import com.ascending.training.model.Account;
import com.ascending.training.model.Department;
import com.ascending.training.model.Employee;
import com.ascending.training.repository.AccountDao;
import com.ascending.training.repository.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private AccountDao accountDao;

// service logic example
    public Employee save(Employee employee){

        Account account = new Account();
        account.setEmployee(employee);


        accountDao.save(account);
        return employeeDao.save(employee);
    }

    public List<Employee> getEmployees(){return employeeDao.getEmployees(); }

    public Employee getBy(Long id){ return employeeDao.getBy(id); }

    public boolean delete(Employee employee){ return employeeDao.delete(employee); }

    public Employee update(Employee employee){return employeeDao.update(employee); }

    public boolean delete(String employeeName){return employeeDao.delete(employeeName); }

    public List<Employee> getEmployeesEager(){return employeeDao.getEmployeesEager(); }

    public Employee getEmployeeEagerBy(Long id){return employeeDao.getEmployeeEagerBy(id); }

    public Employee getEmployeeByName(String employeeName){return employeeDao.getEmployeeByName(employeeName); }



    public Employee getEmployeesAndAccountsBy(String employeeName){return employeeDao.getDepartmentAndEmployeesBy(employeeName);}

    public List<Object[]> getDepartmentAndEmployeesAndAccounts(String employeeName){return employeeDao.getDepartmentAndEmployeesAndAccounts(employeeName); }





    public List<Employee> getByDepartment(Department department){return employeeDao.getByDepartment(department); }



}
