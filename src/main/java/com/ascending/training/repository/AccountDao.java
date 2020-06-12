package com.ascending.training.repository;

import com.ascending.training.model.AccountHQL;
import com.ascending.training.model.DepartmentHQL;

import java.util.List;

public interface AccountDao {

    AccountHQL save(AccountHQL accountHQL);
    List<AccountHQL> getAccountHQL();
    AccountHQL getBy(Long id);
    boolean delete(AccountHQL accountHQL);
    AccountHQL update(AccountHQL account);
    boolean delete(String accountName);
    List<AccountHQL> getAccountsEager();
    AccountHQL getAccountEagerBy(Long id);
    AccountHQL getAccountByName(String accountName);






    AccountHQL getDepartmentAndEmployeesBy(String accountName);  //命名有问题
    List<Object[]> getDepartmentAndEmployeesAndAccounts(String accountName);







}
