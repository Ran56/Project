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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class AccountDaoTest {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountDao accountDao;

    private Account account;

    @Autowired
    private EmployeeDao employeeDao;
    private Employee employee;

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
        employee.setName("Zhangr");
        employee.setAddress("us");
        employee.setDepartment(department);//
        employee.setHiredDate(LocalDate.now());
        employeeDao.save(employee);


        account = new Account();
        account.setAccountType("Checking account");
        account.setBalance(BigDecimal.valueOf(2080.29));
        account.setCreateDate(LocalDate.now());
        account.setEmployee(employee);
        accountDao.save(account);


    }


    @After
    public void tearDown()
    {

        accountDao.delete(account);
        employeeDao.delete(employee);
        departmentDao.delete(department);
    }
    @Test
    public void getAccountsTest()
    {
        List<Account> accountList = new ArrayList<>();
        accountList = accountDao.getAccountHQL();
        int expected = 1;

        Assert.assertEquals(expected, accountList.size());


    }






}
