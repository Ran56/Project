package com.ascending.training.model;

public class Department {
    private long id;
    private String name;
    private String  description;
    private String location;

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



}
