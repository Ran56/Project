package com.ascending.training.repository;

import com.ascending.training.model.Account;
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
public class AccountDaoImpl implements AccountDao{

    @Autowired private SessionFactory sessionFactory;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Account save(Account account) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
//       session.save(department);
//        return department;

        try{
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
            session.close();
            return account;
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
    public List<Account> getAccountHQL() {
        String hql = "From Account";
        Session s = sessionFactory.openSession();
        List<Account> result = new ArrayList<>();

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
    public Account getBy(Long id) {
        return null;
    }

    @Override
    public boolean delete(Account account) {
        String hql = "DELETE Account as acc where acc.id = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<Account> query = session.createQuery(hql);
            query.setParameter("Id", account.getId());
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
    public Account update(Account account) {
        return null;
    }

    @Override
    public boolean delete(String accountName) {
        return false;
    }

    @Override
    public List<Account> getAccountsEager() {
        return null;
    }

    @Override
    public Account getAccountEagerBy(Long id) {
        return null;
    }

    @Override
    public Account getAccountByName(String accountName) {
        return null;
    }

    @Override
    public Account getAccountsAndEmployeeBy(String accountName) {
        return null;
    }

    @Override
    public List<Object[]> getDepartmentAndEmployeesAndAccounts(String accountName) {
        return null;
    }
}
