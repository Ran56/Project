package com.ascending.trainingConsumer.service;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.MessageConsumer;
import java.io.IOException;

@Service
public class ProcessService {
//@Component 写configurations

    @Autowired
    private ProcessService processService;



    private Logger logger = LoggerFactory.getLogger(getClass());
    String receiveNumber = System.getProperty("receiveNumber");
    String sendNumber = System.getProperty("sendNumber");


    @JmsListener(destination = "project-standard-queue")
    public void processMessage(String msg) throws IOException
    {
        System.out.println("project-standard-queue: "+msg);

        MessageCreator messageCreator = processService.SMS(receiveNumber,sendNumber,"Your order has been confirmed!");
        processService.twillioSMSSend(messageCreator);
    }


    @JmsListener(destination = "project-standard-queue2")
    public void processMessage2(String msg) throws IOException
    {
        System.out.println("project-standard-queue2: "+msg);
    }

    public MessageCreator SMS(String receiveNumber, String sendNumber, String messageBody) {
        MessageCreator message = Message.creator(
                new com.twilio.type.PhoneNumber(receiveNumber),
                new com.twilio.type.PhoneNumber(sendNumber),
                messageBody);
        return message;
    }

    public void twillioSMSSend(MessageCreator creator){
        creator.create();
    }

}
