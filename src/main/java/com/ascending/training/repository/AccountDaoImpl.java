package com.ascending.training.repository;

import com.ascending.training.model.AccountHQL;
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

public class AccountDaoImpl implements AccountDao{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public AccountHQL save(AccountHQL accountHQL) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
//       session.save(department);
//        return department;

        try{
            transaction = session.beginTransaction();
            session.save(accountHQL);
            transaction.commit();
            session.close();
            return accountHQL;
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
    public List<AccountHQL> getAccountHQL() {
        String hql = "From AccountHQL";

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        List<AccountHQL> result = new ArrayList<>();

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
    public AccountHQL getBy(Long id) {
        return null;
    }

    @Override
    public boolean delete(AccountHQL accountHQL) {
        String hql = "DELETE AccountHQL as acc where acc.id = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<AccountHQL> query = session.createQuery(hql);
            query.setParameter("Id", accountHQL.getId());
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
    public AccountHQL update(AccountHQL accountHQL) {
        return null;
    }

    @Override
    public boolean delete(String accountName) {
        return false;
    }

    @Override
    public List<AccountHQL> getAccountsEager() {
        return null;
    }

    @Override
    public AccountHQL getAccountEagerBy(Long id) {
        return null;
    }

    @Override
    public AccountHQL getAccountByName(String accountName) {
        return null;
    }

    @Override
    public AccountHQL getDepartmentAndEmployeesBy(String accountName) {
        return null;
    }

    @Override
    public List<Object[]> getDepartmentAndEmployeesAndAccounts(String accountName) {
        return null;
    }
}
