package com.ascending.training.repository;

import com.ascending.training.model.Department;
import com.ascending.training.model.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;


public class DepartmentDaoTestManyToOne {

    private Department d1;
    private Employee e1;
    private Employee e2;

    private DepartmentDao departmentDao;
    private EmployeeDao employeeDao;


@Before
    public void setUp()
{
    d1 = new Department();
    departmentDao = new DepartmentDaoImpl();

    d1.setName("AMZA");
    d1.setDescription("this is AMZA");
    d1.setLocation("Arlington");
    d1 = departmentDao.save(d1);

    employeeDao = new EmployeeDaoImpl();
    e1 = new Employee();
    e1.setName("Zhangr");
    e1.setAddress("us");
    e1.setDepartment(d1);//
    e1.setHiredDate(LocalDate.now());
    employeeDao.save(e1);

    e2 = new Employee();
    e2.setName("LiY");
    e2.setAddress("us");
    e2.setDepartment(d1);//
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
    List<Department> departments = departmentDao.getDepartments();
    int expected =5;
    Assert.assertEquals(expected, departments.size());

}

@Test
    public void getDepartmentEagerByTest()
{
    Department department = departmentDao.getDepartmentEagerBy(d1.getId());
    Assert.assertNotNull(department);
    Assert.assertEquals(department.getName(),d1.getName());
    Assert.assertTrue(department.getEmployeeSet().size()>0);
        //怎么存过去到set里？？？？？

}
}
