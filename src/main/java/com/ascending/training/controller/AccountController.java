package com.ascending.training.controller;


import com.ascending.training.model.Account;
import com.ascending.training.model.Employee;
import com.ascending.training.service.AccountService;
import com.ascending.training.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private EmployeeService employeeService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    //      /account GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Account> getAccounts()
    {
        logger.debug("Retrieve all accounts");
        return accountService.getAccounts();
    }

    //      /account/53 GET
    @RequestMapping(value = "/{Id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable(name = "Id") Long id)
    {
        logger.debug("Retrieve account by id "+id);
        return accountService.getBy(id);
    }

    //      /account/55?accountType=checking PATCH
    @RequestMapping(value = "/{Id}",method = RequestMethod.PATCH)
    public Account updateAccount(@PathVariable(name = "Id") Long id, @RequestParam(name = "accountType") String account_type)
    {
        logger.debug("Updating accountType of account " + id);
        Account account = accountService.getBy(id);
        account.setAccountType(account_type);
        account = accountService.update(account);
        return account;
    }

    //      /account/1 POST
    @RequestMapping(value = "/{Id}",method = RequestMethod.POST)
    public Account create(@PathVariable("Id") Long id, @RequestBody Account account)
    {
        logger.debug("Creating account ");
        Employee employee = employeeService.getBy(id);
        account.setEmployee(employee);
        account = accountService.save(account);
        return account;
    }

    //      /account/50 DELETE
    @RequestMapping(value = "/{Id}",method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable("Id") Long id)
    {
        logger.debug("Deleting account by id "+id);
        Account account = accountService.getBy(id);
        Boolean result = accountService.delete(account);
        return result;
    }










}
