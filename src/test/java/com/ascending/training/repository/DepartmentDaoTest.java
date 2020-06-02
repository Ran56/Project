package com.ascending.training.repository;

import com.ascending.training.model.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepartmentDaoTest {
    private  DepartmentDAO departmentJDBCDAO;
    @Before
    public void setUP()
    {
         departmentJDBCDAO = new DepartmentDAO();
    }
    @After
    public void tearDown()
    {
        departmentJDBCDAO = null;
    }
    @Test
    public void getDepartmentsTest()
    {
//aaaaaa

        assertEquals(4,departmentJDBCDAO.getDepartments().size());
    }





}
