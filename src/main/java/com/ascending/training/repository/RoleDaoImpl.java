package com.ascending.training.repository;

import com.ascending.training.model.Role;
import com.ascending.training.model.User;
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

@Repository
public class RoleDaoImpl implements RoleDao{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SessionFactory sessionFactory;



    @Override
    public Role getRoleByName(String name) {
        String hql = "From Role r where r.name =:name";
        Session session = sessionFactory.openSession();
        try {
            Query<Role> query = session.createQuery(hql);
            query.setParameter("name",name);
            Role result = query.uniqueResult();
            session.close();
            return result;
        }catch (HibernateException e){
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
    }


    @Override
    public List<Role> findAllRoles() {
        String hql = "From Role";
        Session s = sessionFactory.openSession();
        List<Role> result = new ArrayList<>();

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
    public Role findById(Long id) {
        String hql = "FROM role r where r.id=:Id";
        Session session = sessionFactory.openSession();
        try
        {
            Query<Role> query = session.createQuery(hql);
            query.setParameter("Id",id);
            Role role = query.uniqueResult();
            session.close();
            return  role;
        }
        catch (Exception e)
        {
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }

    }

    @Override
    public Role save(Role role) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            session.close();
            return role;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("failure to insert record", e);
            session.close();
            return null;
        }
    }

    @Override
    public void delete(Role role) {
        String hql = "DELETE Role as r where r.id = :Id";
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<Role> query = session.createQuery(hql);
            query.setParameter("Id", role.getId());
            query.executeUpdate();
            transaction.commit();
            session.close();
        }
        catch(HibernateException e)
        {
            if(transaction != null) transaction.rollback();
            session.close();
            logger.error("unable to delete record", e);
        }
    }

    @Override
    public Role update(Role role) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.update(role);
            transaction.commit();
            session.close();
            return role;
        }
        catch(Exception e)
        {
            if(transaction != null) transaction.rollback();
            logger.error("failure to update record",e);
            session.close();
            return null;
        }
    }
}
