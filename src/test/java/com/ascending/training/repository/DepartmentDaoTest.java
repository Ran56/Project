package com.ascending.training.repository;

import com.ascending.training.model.Department;
import com.ascending.training.model.DepartmentHQL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepartmentDaoTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private DepartmentDao departmentDao;
    private DepartmentHQL departmentHQL;

   @Before
   public void setUp()
   {
       departmentDao = new DepartmentDaoImpl();
       departmentHQL = new DepartmentHQL();
       departmentHQL.setName("MIRCO");
       departmentHQL.setDescription("technological company");
       departmentHQL.setLocation("addddd");
       departmentDao.save(departmentHQL);


   }
    @After
    public void tearDown()
    {

        departmentDao.delete(departmentHQL);
    }

    @Test
    public void getDepartmentsTest()
    {
        List<DepartmentHQL> departments = departmentDao.getDepartments();
        int expectedNumOfDept = 1;

        Assert.assertEquals(expectedNumOfDept,departments.size());

    }




}
