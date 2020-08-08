package com.ascending.trainingConsumer;


import com.amazonaws.services.sqs.model.Message;
import com.ascending.trainingConsumer.service.SQSMessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import java.io.IOException;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.ascending.trainingConsumer")
public class ApplicationInitializer {


    public static void main(String[] args) throws IOException {

      SpringApplication.run(ApplicationInitializer.class,args);


//        ConfigurableApplicationContext app = SpringApplication.run(ApplicationInitializer.class,args);
//        SQSMessageService messageService = app.getBean(SQSMessageService.class);
////         equals to
////        @Autowired
////        SQSMessageService sqsMessageService;
//
//        messageService.receiveMessage();

        //这只是没有listener时简单的做一下receiveMessage，需要额外加一个listener监听message，一收到就会对
        //message进行处理，届时上面三行代码就可以删掉了只需要SpringApplication.run(ApplicationInitializer.class,args);
    //写mockito的unit test


//
//
//        ConfigurableApplicationContext app = SpringApplication.run(ApplicationInitializer.class, args);
//        SQSMessageService messageService = app.getBean(SQSMessageService.class);
//
//        List<Message> messages=messageService.receiveMessage();
//        for (Message m:messages){
//            messageService.processMessage(m.getBody());
//        }

    }

}
