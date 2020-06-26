package com.ascending.training.repository;

import com.ascending.training.jdbc.DepartmentDAO;
import com.ascending.training.jdbc.DepartmentJDBC;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class DepartmentDaoTestJDBC {
    private static Logger logger = LoggerFactory.getLogger(DepartmentDaoTestJDBC.class);
    private DepartmentDAO departmentJDBCDAO;
    private DepartmentJDBC department;
    @Before
    public void setUP()
    {
         departmentJDBCDAO = new DepartmentDAO();
         department = new DepartmentJDBC();
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

        String oldName = "HR";
        //departmentJDBCDAO = getDepartmentsTest();
        department.setName("Marketing");
        department.setDescription("this is a marketing department");
        department.setLocation("Crystal city VA");

        assertEquals(1, departmentJDBCDAO.update(oldName,department));
        assertNotSame(oldName,department.getName());
        logger.info("Update data succeed");
    }
    @Test
    public void delete()
    {
        department.setName("Marketing");
        assertEquals(1,departmentJDBCDAO.delete(department.getName()));
        logger.info("Delete data succeed");
    }
    @Test
    public void save ()
    {
        department.setName("Alibaba");
        department.setDescription("technological company");
        department.setLocation("addddd");
        logger.info("Input succeed");
        assertEquals(1,departmentJDBCDAO.save(department));
        logger.info("Insert data succeed");
    }






}
