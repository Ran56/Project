package com.ascending.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Hello world!
 *
 */
  class App implements Comparable<App>{
    String name;
    int age;

    public App(String name, int age)
    {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    @Override
    public int compareTo(App p)
    {
        return this.age-p.getAge();
    }

    public static void main(String[] args)
    {

        List<App> list = new ArrayList<>();
        list.add(new App("xujian", 70));


        list.add(new App("Yujian", 50));


        list.add(new App("xiewei", 20));

        System.out.println("排序前");
        for (App person : list)
        {
            System.out.print(person.getName()+":"+person.getAge());
        }


        Collections.sort(list);
        System.out.println("\n排序后");
        for (App person : list)
        {
            System.out.print(person.getName()+":"+person.getAge());
        }
    }

}




//
//interface TestIs
//{
//    int ab = 20;
//    public void hours();
//
//
//}

//enum Level
//{
//    LOW,
//    MEDIUM,
//    HIGH
//}
//
//
