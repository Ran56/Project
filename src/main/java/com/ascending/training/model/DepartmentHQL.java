package com.ascending.training.model;



import javax.persistence.*;
import java.util.Set;
//persistent object template:

@Entity //domain, model, entity都是表达同一个东西在这里用@Entity
@Table (name = "departments")
public class DepartmentHQL {
   @Id  //标示这是primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)  //标示这是Bigserial 自动加1
   @Column(name = "id")
    private long id;

   @Column(name = "name")
    private String name;

   @Column(name = "description")
    private String  description;

   @Column(name = "location")
    private String location;


   @OneToMany(mappedBy = "departmentHQL", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<EmployeeHQL> employeeHQLSet;




    public DepartmentHQL(){ }


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

    public void setEmployeeHQLSet (EmployeeHQL employeeHQL)
    {
        employeeHQLSet.add(employeeHQL);
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

    public Set<EmployeeHQL> getEmployeeHQLSet ()
    {
        try
        {
            int size = employeeHQLSet.size();
        }
        catch (Exception e)
        {
            return null;
        }
        return employeeHQLSet;
    }





}
