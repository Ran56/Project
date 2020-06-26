package com.ascending.training.repository;

import com.ascending.training.ApplicationBootstrap;
import com.ascending.training.model.Department;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes= ApplicationBootstrap.class)

public class DepartmentDaoTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private DepartmentDao departmentDao;
    private Department department;

   @Before
   public void setUp()
   {
       departmentDao = new DepartmentDaoImpl();
       department = new Department();
       department.setName("MIRCO");
       department.setDescription("technological company");
       department.setLocation("addddd");
       departmentDao.save(department);


   }
    @After
    public void tearDown()
    {
        departmentDao.delete(department);
    }

    @Test
    public void getDepartmentsTest()
    {
        List<Department> departments = departmentDao.getDepartments();
        int expectedNumOfDept = 5;

        Assert.assertEquals(expectedNumOfDept,departments.size());

    }




}
