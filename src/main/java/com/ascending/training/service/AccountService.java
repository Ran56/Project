package com.ascending.training.service;

import com.ascending.training.model.Account;
import com.ascending.training.repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    public Account save(Account account){ return accountDao.save(account); }

    public List<Account> getAccountHQL(){return accountDao.getAccountHQL(); }

    public Account getBy(Long id){return accountDao.getBy(id); }

    public boolean delete(Account account){return accountDao.delete(account); }

    public Account update(Account account){return accountDao.update(account); }

    public boolean delete(String accountName){return accountDao.delete(accountName); }

    public List<Account> getAccountsEager(){return accountDao.getAccountsEager(); }

    public Account getAccountEagerBy(Long id){return accountDao.getAccountEagerBy(id); }

    public Account getAccountByName(String accountName){return accountDao.getAccountByName(accountName); }



    public Account getAccountsAndEmployeeBy(String accountName){return accountDao.getAccountsAndEmployeeBy(accountName); }  //命名有问题

    public List<Object[]> getDepartmentAndEmployeesAndAccounts(String accountName){return accountDao.getDepartmentAndEmployeesAndAccounts(accountName); }

}
