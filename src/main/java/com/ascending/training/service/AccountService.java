package com.ascending.training.service;

import com.ascending.training.model.AccountHQL;
import com.ascending.training.repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    public AccountHQL save(AccountHQL accountHQL){ return accountDao.save(accountHQL); }

    public List<AccountHQL> getAccountHQL(){return accountDao.getAccountHQL(); }

    public AccountHQL getBy(Long id){return accountDao.getBy(id); }

    public boolean delete(AccountHQL accountHQL){return accountDao.delete(accountHQL); }

    public AccountHQL update(AccountHQL account){return accountDao.update(account); }

    public boolean delete(String accountName){return accountDao.delete(accountName); }

    public List<AccountHQL> getAccountsEager(){return accountDao.getAccountsEager(); }

    public AccountHQL getAccountEagerBy(Long id){return accountDao.getAccountEagerBy(id); }

    public AccountHQL getAccountByName(String accountName){return accountDao.getAccountByName(accountName); }



    public AccountHQL getAccountsAndEmployeeBy(String accountName){return accountDao.getAccountsAndEmployeeBy(accountName); }  //命名有问题

    public List<Object[]> getDepartmentAndEmployeesAndAccounts(String accountName){return accountDao.getDepartmentAndEmployeesAndAccounts(accountName); }

}
