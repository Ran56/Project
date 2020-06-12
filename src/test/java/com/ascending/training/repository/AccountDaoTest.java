package com.ascending.training.repository;

import com.ascending.training.model.AccountHQL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoTest {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private AccountDao accountDao;
    private AccountHQL accountHQL;



    @Before
    public void setUp()
    {
        accountHQL = new AccountHQL();
        accountHQL.setAccount_type("Checking account");
        accountHQL.setBalance(BigDecimal.valueOf(2080.29));
        accountHQL.setCreate_date("2020-02-12");
        //accountHQL.setEmployee_id(1);
        accountDao = new AccountDaoImpl();
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
