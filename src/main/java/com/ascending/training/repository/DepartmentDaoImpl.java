package com.ascending.training.repository;

import com.ascending.training.model.Department;
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



@Repository //创建@Bean，加了@Repository后系统会默认该类实例为Bean
public class DepartmentDaoImpl implements DepartmentDao{
    @Autowired
    private SessionFactory sessionFactory;
//    @Autowired
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Department save(Department department) {  //dummy implementation 意思就是没有任何实现
       Transaction transaction = null;
       Session session = sessionFactory.openSession();

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
    public boolean delete(Department dep) {
        String hql = "DELETE Department as dep where dep.id = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<Department> query = session.createQuery(hql);
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
    public List<Department> getDepartments() {
        String hql = "From Department";
        Session s = sessionFactory.openSession();
        List<Department> result = new ArrayList<>();

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
    public Department getDepartmentEagerBy(Long id) {
        //        select * from departments as dep left join employees as e on a.employee_id=dep.id where dep.id=:Id

        String hql = "FROM Department d LEFT JOIN FETCH d.employeeSet where d.id=:Id";
        Session session = sessionFactory.openSession();
        try {
            Query<Department> query = session.createQuery(hql);
            query.setParameter("Id",id);
            Department result = query.uniqueResult();
            session.close();
            return result;
        }catch (HibernateException e){
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
    }





    @Override
    public Department getBy(Long id) {
//join fetch d.employeeSet
        String hql = "FROM Department d where d.id=:Id";
        Session session = sessionFactory.openSession();
        try {
            Query<Department> query = session.createQuery(hql);
            query.setParameter("Id",id);
            Department result = query.uniqueResult();
            session.close();
            return result;
        }catch (HibernateException e){
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }

    }

    @Override
    public Department update(Department department) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.update(department);
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
    public boolean delete(String deptName) {
        return false;
    }

    @Override
    public List<Department> getDepartmentsEager() {
        return null;
    }


    @Override
    public Department getDepartmentByName(String deptName) {
        String hql = "From Department d where d.name =:deptName";
        Session session = sessionFactory.openSession();
        try {
            Query<Department> query = session.createQuery(hql);
            query.setParameter("deptName",deptName);
            Department result = query.uniqueResult();
            session.close();
            return result;
        }catch (HibernateException e){
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
    }

    @Override
    public Department getDepartmentAndEmployeesBy(String deptName) {
        return null;
    }

    @Override
    public List<Object[]> getDepartmentAndEmployeesAndAccounts(String deptName) {
        return null;
    }
}
