package com.ascending.training.repository;

import com.ascending.training.model.DepartmentHQL;
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

public class DepartmentDaoImpl implements DepartmentDao{
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public DepartmentHQL save(DepartmentHQL department) {  //dummy implementation 意思就是没有任何实现
       Transaction transaction = null;
       Session session = HibernateUtil.getSessionFactory().openSession();

       try{
           transaction = session.beginTransaction();
           session.save(department);
           transaction.commit();
           session.close();
           return department;
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
    public boolean delete(DepartmentHQL dep) {
        String hql = "DELETE DepartmentHQL as dep where dep.id = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<DepartmentHQL> query = session.createQuery(hql);
            query.setParameter("Id", dep.getId());
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
    public List<DepartmentHQL> getDepartments() {
        String hql = "From DepartmentHQL";

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        List<DepartmentHQL> result = new ArrayList<>();

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
    public DepartmentHQL getDepartmentEagerBy(Long id) {
        //        select * from departments as dep left join employees as e on a.employee_id=dep.id where dep.id=:Id
        String hql = "FROM DepartmentHQL d LEFT JOIN FETCH d.employeeHQLSet where d.id=:Id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<DepartmentHQL> query = session.createQuery(hql);
            query.setParameter("Id",id);
            DepartmentHQL result = query.uniqueResult();
            session.close();
            return result;
        }catch (HibernateException e){
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
    }





    @Override
    public DepartmentHQL getBy(Long id) {
        return null;
    }

    @Override
    public DepartmentHQL update(DepartmentHQL department) {
        return null;
    }

    @Override
    public boolean delete(String deptName) {
        return false;
    }

    @Override
    public List<DepartmentHQL> getDepartmentsEager() {
        return null;
    }


    @Override
    public DepartmentHQL getDepartmentByName(String deptName) {
        return null;
    }

    @Override
    public DepartmentHQL getDepartmentAndEmployeesBy(String deptName) {
        return null;
    }

    @Override
    public List<Object[]> getDepartmentAndEmployeesAndAccounts(String deptName) {
        return null;
    }
}
