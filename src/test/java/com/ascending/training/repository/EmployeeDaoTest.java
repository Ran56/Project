package com.ascending.training.repository;

import com.ascending.training.ApplicationBootstrap;
import com.ascending.training.model.Account;
import com.ascending.training.model.Department;
import com.ascending.training.model.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class EmployeeDaoTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private EmployeeDao employeeDao;
    private Employee employee;
    private Account account1;
    private Account account2;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private DepartmentDao departmentDao;

    private Department department;


    @Before
    public void setUp()
    {

        department = new Department();
        department.setName("AMZA");
        department.setDescription("this is AMZA");
        department.setLocation("Arlington");
        department = departmentDao.save(department);


        employee = new Employee();
        employee.setName("Jan Jia");
        employee.setDepartment(department);
        employeeDao.save(employee);

        account1 = new Account();
        account1.setAccountType("Debit");
        account1.setBalance(BigDecimal.valueOf(1000));
        account1.setEmployee(employee);
        accountDao.save(account1);

        account2 = new Account();
        account2.setAccountType("Debit");
        account2.setBalance(BigDecimal.valueOf(1670));
        account2.setEmployee(employee);
        accountDao.save(account2);

    }
    @After
    public void tearDown()
    {

        accountDao.delete(account1);
        accountDao.delete(account2);
        employeeDao.delete(employee);
        departmentDao.delete(department);

    }
    @Test
    public void getEmployeesTest()
    {
        int expected = 3;
        Assert.assertEquals(expected,employeeDao.getEmployees().size());
    }

    @Test
    public void getEmployeeEagerByTest()
    {
        Employee employeeHQLa =  employeeDao.getEmployeeEagerBy(employee.getId());
        Assert.assertNotNull(employeeHQLa);
        Assert.assertEquals(employeeHQLa.getName(), employee.getName());
        Assert.assertTrue(employeeHQLa.getAccountSet().size() > 0);


    }



    @Test
    public void getEmployeeByDepartmentTest()
    {

        Employee employee = employeeDao.getByDepartment(department);
        //int expected =1;
        Assert.assertNotNull(employee);

    }





}
