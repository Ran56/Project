package com.ascending.training.repository;

import com.ascending.training.model.Department;
import com.ascending.training.model.Employee;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository//创建@Bean，加了@Repository后系统会默认该类实例为Bean
//找到有annotation @Repository的类后会自动生成 EmployeeDao employeeDao = new EmployeeDaoImpl();
public class EmployeeDaoImpl implements EmployeeDao{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired private SessionFactory sessionFactory;

    @Override
    public Employee save(Employee employee) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            session.close();
            return employee;
        }
        catch(Exception e)
        {
            if(transaction != null) transaction.rollback();
            logger.error("failure to insert record",e);
            session.close();
            return null;
        }
    }

    @Override
    public List<Employee> getEmployees() {
        String hql = "From Employee";
        Session s = sessionFactory.openSession();
        List<Employee> result = new ArrayList<>();

        try {
            Query query = s.createQuery(hql);
            result = query.list();
            s.close();
        }
        catch(HibernateException e)
        {
            logger.error("session close exception try again...",e);
            s.close();
        }
        return result;
    }

    @Override
    public Employee getBy(Long id) {
        return null;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Employee getByDepartment(Department department)
    {
        String hql = "FROM Employee d LEFT JOIN FETCH d.department de where de.id=:Id";
        Session session = sessionFactory.openSession();
        try {
            Query<Employee> query = session.createQuery(hql);
            query.setParameter("Id", department.getId());
            Employee result = query.uniqueResult();
            session.close();
            return result;
        }catch (HibernateException e){
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
    }}
/////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean delete(Employee employee) {
        String hql = "DELETE Employee as emp where emp.id = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery(hql);
            query.setParameter("Id", employee.getId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            session.close();
            return deletedCount >= 1 ? true : false;
        }
        catch(HibernateException e)
        {
            if(transaction != null) transaction.rollback();
            session.close();
            logger.error("unable to delete record", e);
        }
        return false;
    }

    @Override
    public Employee update(Employee employee) {
        return null;
    }

    @Override
    public boolean delete(String employeeName) {
        return false;
    }

    @Override
    public List<Employee> getEmployeesEager() {
        return null;
    }

    @Override
    public Employee getEmployeeEagerBy(Long id) {
        String hql = "FROM Employee d LEFT JOIN FETCH d.accountSet where d.id=:Id";
        Session session = sessionFactory.openSession();
        try {
            Query<Employee> query = session.createQuery(hql);
            query.setParameter("Id",id);
            Employee result = query.uniqueResult();
            session.close();
            return result;
        }catch (HibernateException e){
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
    }



    @Override
    public Employee getEmployeeByName(String deptName) {
        return null;
    }

    @Override
    public Employee getDepartmentAndEmployeesBy(String deptName) {
        return null;
    }

    @Override
    public List<Object[]> getDepartmentAndEmployeesAndAccounts(String deptName) {
        return null;
    }
}
