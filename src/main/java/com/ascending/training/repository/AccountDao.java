package com.ascending.training.repository;

import com.ascending.training.model.Account;

import java.util.List;

public interface AccountDao {

    Account save(Account account);
    List<Account> getAccounts();
    Account getBy(Long id);
    boolean delete(Account account);
    Account update(Account account);
    boolean delete(String accountName);
    List<Account> getAccountsEager();
    Account getAccountEagerBy(Long id);
    Account getAccountByName(String accountName);



    Account getAccountsAndEmployeeBy(String accountName);
    List<Object[]> getDepartmentAndEmployeesAndAccounts(String accountName);







}
