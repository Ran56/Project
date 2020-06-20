package com.ascending.training.repository;

import com.ascending.training.ApplicationBootstrap;
import com.ascending.training.model.DepartmentHQL;
import com.ascending.training.model.EmployeeHQL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;


public class DepartmentDaoTestManyToOne {

    private DepartmentHQL d1;
    private EmployeeHQL e1;
    private EmployeeHQL e2;

    private DepartmentDao departmentDao;
    private EmployeeDao employeeDao;


@Before
    public void setUp()
{
    d1 = new DepartmentHQL();
    departmentDao = new DepartmentDaoImpl();

    d1.setName("AMZA");
    d1.setDescription("this is AMZA");
    d1.setLocation("Arlington");
    d1 = departmentDao.save(d1);

    employeeDao = new EmployeeDaoImpl();
    e1 = new EmployeeHQL();
    e1.setName("Zhangr");
    e1.setAddress("us");
    e1.setDepartmentHQL(d1);//
    e1.setHiredDate(LocalDate.now());
    employeeDao.save(e1);

    e2 = new EmployeeHQL();
    e2.setName("LiY");
    e2.setAddress("us");
    e2.setDepartmentHQL(d1);//
    employeeDao.save(e2);


}
@After
    public void tearDown()
{

    employeeDao.delete(e1);
    employeeDao.delete(e2);
    departmentDao.delete(d1);
}


@Test
    public void getDepartmentsTest()
{
    List<DepartmentHQL> departmentHQLS = departmentDao.getDepartments();
    int expected =5;
    Assert.assertEquals(expected,departmentHQLS.size());

}

@Test
    public void getDepartmentEagerByTest()
{
    DepartmentHQL departmentHQL = departmentDao.getDepartmentEagerBy(d1.getId());
    Assert.assertNotNull(departmentHQL);
    Assert.assertEquals(departmentHQL.getName(),d1.getName());
    Assert.assertTrue(departmentHQL.getEmployeeHQLSet().size()>0);
        //怎么存过去到set里？？？？？

}
}
