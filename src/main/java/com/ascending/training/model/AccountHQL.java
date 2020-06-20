package com.ascending.training.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity //domain, model, entity都是表达同一个东西在这里用@Entity
@Table(name = "accounts")
public class AccountHQL {



    @Id  //标示这是primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //标示这是Bigserial 自动加1
    @Column(name = "id")
    private long id;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance")
    private BigDecimal  balance;

    @Column(name = "create_date")
    private LocalDate createDate;
//
//    @Column(name = "employee_id")   有个多对一（@manyToOne）立体对平面的关系就不需要再
//    一一对应的employee_id了，与其相关的方法等也可以删除掉
//    private long employee_id;
//
//
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeHQL employee;







    public void setId(long id)
    {
        this.id = id;
    }

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    public void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }

    public void setCreateDate(LocalDate createDate)
    {
        this.createDate = createDate;
    }

    public void setEmployee(EmployeeHQL employee)
    {
        this.employee = employee;
    }



    public long getId()
    {
        return id;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public BigDecimal getBalance()
    {
        return balance;
    }

    public LocalDate getCreateDate()
    {
        return createDate;
    }

    public EmployeeHQL getEmployee()
    {
        return employee;
    }




}
