package com.ascending.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
 public  class App implements TestIs
{
    private Logger logger1 = LoggerFactory.getLogger(getClass());

    class Te
    {
        int aaaa=20;
    }

    public static void main( String[] args )

    {

        App appp = new App();
        int a = 20;
        int b = 30;
        System.out.println(a+b);

        Level level = Level.MEDIUM;
        switch (level)
        {
            case LOW:
                System.out.println("LOW");
                break;
            case MEDIUM:
                System.out.println("MEDIUM");
                break;
            case HIGH:
                System.out.println("HIGH");
                break;
        }
        appp.logger1.info("hello world"+" "+(a+b));
        System.out.println(level);

    }

    public void hours()
    {
        System.out.println(ab);
    }
}


interface TestIs
{
    int ab = 20;
    public void hours();


}

enum Level
{
    LOW,
    MEDIUM,
    HIGH
}


