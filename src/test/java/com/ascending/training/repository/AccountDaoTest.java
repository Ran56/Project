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
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class AccountDaoTest {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountDao accountDao;

    private AccountHQL accountHQL;

    @Autowired
    private EmployeeDao employeeDao;
    private EmployeeHQL employeeHQL;

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
        employeeHQL.setName("Zhangr");
        employeeHQL.setAddress("us");
        employeeHQL.setDepartmentHQL(departmentHQL);//
        employeeHQL.setHiredDate(LocalDate.now());
        employeeDao.save(employeeHQL);


        accountHQL = new AccountHQL();
        accountHQL.setAccountType("Checking account");
        accountHQL.setBalance(BigDecimal.valueOf(2080.29));
        accountHQL.setCreateDate(LocalDate.now());
        accountHQL.setEmployee(employeeHQL);
        accountDao.save(accountHQL);


    }


    @After
    public void tearDown()
    {

        accountDao.delete(accountHQL);
    }
    @Test
    public void getAccountsTest()
    {
        List<AccountHQL> accountHQLList = new ArrayList<>();
        accountHQLList = accountDao.getAccountHQL();
        int expected = 1;

        Assert.assertEquals(expected,accountHQLList.size());


    }






}
