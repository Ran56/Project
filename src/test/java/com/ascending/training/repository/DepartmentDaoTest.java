package com.ascending.training.repository;

import com.ascending.training.model.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

        assertEquals(4,departmentJDBCDAO.getDepartments().size());
    }
    @Test
    public void update()
    {
        assertTrue(-1 < departmentJDBCDAO.update());
    }
    @Test
    public void delete()
    {
        assertTrue(0 < departmentJDBCDAO.delete());
    }
    @Test
    public void add ()
    {
        assertTrue(0 < departmentJDBCDAO.add());
    }






}
