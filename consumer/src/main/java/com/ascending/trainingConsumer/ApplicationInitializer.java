package com.ascending.trainingConsumer;


import com.ascending.trainingConsumer.service.SQSMessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.ascending.trainingConsumer")
public class ApplicationInitializer {

    public static void main(String[] args)
    {
        ConfigurableApplicationContext app = SpringApplication.run(ApplicationInitializer.class,args);
        SQSMessageService messageService = app.getBean(SQSMessageService.class);
//        SpringApplication.run(ApplicationInitializer.class,args);
        messageService.receiveMessage();
    }

}
