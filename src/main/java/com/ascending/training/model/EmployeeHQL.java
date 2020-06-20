package com.ascending.training.model;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity //domain, model, entity都是表达同一个东西在这里用@Entity
@Table(name = "employees")
public class EmployeeHQL {

    @Id  //标示这是primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //标示这是Bigserial 自动加1
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "hired_date")
    private LocalDate hiredDate;

//    @Column(name = "department_id")  //与AccountHQL中的同理
//    private long department_id;

//
//
//mappedBy = 的是一个AccountHQL中@ManyToOne对应的变量名
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<AccountHQL> accountHQLSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private DepartmentHQL departmentHQL;


    public void setId(long id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setHiredDate(LocalDate hiredDate)
    {
        this.hiredDate = hiredDate;
    }

    public void setDepartmentHQL (DepartmentHQL departmentHQL) {this.departmentHQL = departmentHQL;}



   // public void setDepartment_id(long department_id)
//    {
//        this.department_id = department_id;
//    }


    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String  getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getAddress()
    {
        return address;
    }

    public LocalDate getHiredDate()
    {
        return hiredDate;
    }

    public DepartmentHQL getDepartmentHQL() { return departmentHQL; }

    public Set<AccountHQL> getAccountHQLSet(){
        try
        {
            int size = accountHQLSet.size();
        }
        catch (Exception e)
        {
            return null;
        }
        return accountHQLSet;

    }

//    public long getDepartment_id()
//    {
//        return department_id;
//    }





}
