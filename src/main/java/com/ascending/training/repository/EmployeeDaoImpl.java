package com.ascending.training.repository;

import com.ascending.training.model.DepartmentHQL;
import com.ascending.training.model.EmployeeHQL;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class EmployeeDaoImpl implements EmployeeDao{
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public EmployeeHQL save(EmployeeHQL employeeHQL) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();


        try{
            transaction = session.beginTransaction();
            session.save(employeeHQL);
            transaction.commit();
            session.close();
            return employeeHQL;
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
    public List<EmployeeHQL> getEmployees() {
        String hql = "From EmployeeHQL";

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        List<EmployeeHQL> result = new ArrayList<>();

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
    public EmployeeHQL getBy(Long id) {
        return null;
    }

    @Override
    public boolean delete(EmployeeHQL employeeHQL) {
        String hql = "DELETE EmployeeHQL as emp where emp.id = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<EmployeeHQL> query = session.createQuery(hql);
            query.setParameter("Id", employeeHQL.getId());
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
    public EmployeeHQL update(EmployeeHQL employee) {
        return null;
    }

    @Override
    public boolean delete(String employeeName) {
        return false;
    }

    @Override
    public List<EmployeeHQL> getEmployeesEager() {
        return null;
    }

    @Override
    public EmployeeHQL getEmployeeEagerBy(Long id) {
        return null;
    }

    @Override
    public EmployeeHQL getEmployeeByName(String deptName) {
        return null;
    }

    @Override
    public EmployeeHQL getDepartmentAndEmployeesBy(String deptName) {
        return null;
    }

    @Override
    public List<Object[]> getDepartmentAndEmployeesAndAccounts(String deptName) {
        return null;
    }
}
