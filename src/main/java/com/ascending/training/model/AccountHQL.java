package com.ascending.training.model;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity //domain, model, entity都是表达同一个东西在这里用@Entity
@Table(name = "accounts")
public class AccountHQL {



    @Id  //标示这是primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //标示这是Bigserial 自动加1
    @Column(name = "id")
    private long id;

    @Column(name = "account_type")
    private String account_type;

    @Column(name = "balance")
    private BigDecimal  balance;

    @Column(name = "create_date")
    private String create_date;
//
//    @Column(name = "employee_id")   有个多对一（@manyToOne）立体对平面的关系就不需要再
//    一一对应的employee_id了，与其相关的方法等也可以删除掉
//    private long employee_id;
//
//
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeHQL employeeHQL;







    public void setId(long id)
    {
        this.id = id;
    }

    public void setAccount_type(String account_type)
    {
        this.account_type = account_type;
    }

    public void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }

    public void setCreate_date(String create_date)
    {
        this.create_date = create_date;
    }

//    public void setEmployee_id(long employee_id)
//    {
//        this.employee_id = employee_id;
//    }



    public long getId()
    {
        return id;
    }

    public String getAccount_type()
    {
        return account_type;
    }

    public BigDecimal getBalance()
    {
        return balance;
    }

    public String getCreate_date()
    {
        return create_date;
    }
//
//    public long getEmployee_id()
//    {
//        return employee_id;
//    }




}
