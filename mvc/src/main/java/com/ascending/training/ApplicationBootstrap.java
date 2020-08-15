package com.ascending.training;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackages = {"com.ascending.training"})
@ServletComponentScan(basePackages = {"com.ascending.training.filter"})
public class ApplicationBootstrap extends SpringBootServletInitializer {


    public static void main(String[] args)
    {
        SpringApplication.run(ApplicationBootstrap.class, args);
    }








}
