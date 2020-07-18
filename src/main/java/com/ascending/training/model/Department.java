package com.ascending.training.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;
//persistent object template:

@Entity //domain, model, entity都是表达同一个东西在这里用@Entity
@Table (name = "departments")
public class Department {

//    public static class ExtendView extends BasicView{}
//    public static class BasicView{}
//
//public static class ExtendedView extends BasicView{}
//public static class BasicView{}

   @Id  //标示这是primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)  //标示这是Bigserial 自动加1
   @Column(name = "id")
//   @JsonView(BasicView.class)
    private long id;

//   @JsonView(BasicView.class)
   @Column(name = "name")
    private String name;

   @Column(name = "description")
    private String  description;

   @Column(name = "location")
    private String location;


   @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
   @JsonIgnore
//   @JsonView(ExtendedView.class)
    private Set<Employee> employeeSet;




    public Department(){ }


    public void setId(long id)
    {
       this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setEmployeeSet (Employee employee)
    {
        employeeSet.add(employee);
    }






    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public String getLocation()
    {
        return location;
    }

    public Set<Employee> getEmployeeSet ()
    {
        try
        {
            int size = employeeSet.size();
        }
        catch (Exception e)
        {
            return null;
        }
        return employeeSet;
    }





}
