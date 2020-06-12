package com.ascending.training.repository;

import com.ascending.training.model.EmployeeHQL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeDaoTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private EmployeeDao employeeDao;
    private EmployeeHQL employeeHQL;

    @Before
    public void setUp()
    {

        employeeHQL = new EmployeeHQL();
        employeeDao = new EmployeeDaoImpl();
        employeeHQL.setName("Jan Jia");
        employeeHQL.setFirst_name("Jan");
        employeeHQL.setLast_name("Jia");
       // employeeHQL.setDepartment_id(49);
        employeeDao.save(employeeHQL);

    }
    @After
    public void tearDown()
    {
        employeeDao.delete(employeeHQL);
    }
    @Test
    public void getEmployeesTest()
    {
        int expected = 2;
        Assert.assertEquals(expected,employeeDao.getEmployees().size());
    }





}
