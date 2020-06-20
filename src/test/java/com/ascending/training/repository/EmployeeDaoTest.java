package com.ascending.training.repository;

import com.ascending.training.ApplicationBootstrap;
import com.ascending.training.model.AccountHQL;
import com.ascending.training.model.DepartmentHQL;
import com.ascending.training.model.EmployeeHQL;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class EmployeeDaoTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private EmployeeDao employeeDao;
    private EmployeeHQL employeeHQL;
    private AccountHQL accountHQL1;
    private AccountHQL accountHQL2;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private DepartmentDao departmentDao;

    private DepartmentHQL departmentHQL;


    @Before
    public void setUp()
    {

        departmentHQL = new DepartmentHQL();
        departmentHQL.setName("AMZA");
        departmentHQL.setDescription("this is AMZA");
        departmentHQL.setLocation("Arlington");
        departmentHQL = departmentDao.save(departmentHQL);


        employeeHQL = new EmployeeHQL();
        employeeHQL.setName("Jan Jia");
        employeeHQL.setDepartmentHQL(departmentHQL);
        employeeDao.save(employeeHQL);

        accountHQL1 = new AccountHQL();
        accountHQL1.setAccountType("Debit");
        accountHQL1.setBalance(BigDecimal.valueOf(1000));
        accountHQL1.setEmployee(employeeHQL);
        accountDao.save(accountHQL1);

        accountHQL2 = new AccountHQL();
        accountHQL2.setAccountType("Debit");
        accountHQL2.setBalance(BigDecimal.valueOf(1670));
        accountHQL2.setEmployee(employeeHQL);
        accountDao.save(accountHQL2);

    }
    @After
    public void tearDown()
    {

        accountDao.delete(accountHQL1);
        accountDao.delete(accountHQL2);
        employeeDao.delete(employeeHQL);
        departmentDao.delete(departmentHQL);

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
        EmployeeHQL employeeHQLa =  employeeDao.getEmployeeEagerBy(employeeHQL.getId());
        Assert.assertNotNull(employeeHQLa);
        Assert.assertEquals(employeeHQLa.getName(),employeeHQL.getName());
        Assert.assertTrue(employeeHQLa.getAccountHQLSet().size() > 0);


    }



    @Test
    public void getEmployeeByDepartmentTest()
    {

        EmployeeHQL employeeHQL = employeeDao.getByDepartment(departmentHQL);
        //int expected =1;
        Assert.assertNotNull(employeeHQL);

    }





}
