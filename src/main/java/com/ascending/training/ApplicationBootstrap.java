package com.ascending.training;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;


@SpringBootApplication(scanBasePackages = {"com.ascending.training"})
public class ApplicationBootstrap {


    public static void main(String[] args)
    {
        SpringApplication.run(ApplicationBootstrap.class, args);
    }








}
