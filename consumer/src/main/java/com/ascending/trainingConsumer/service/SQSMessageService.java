package com.ascending.trainingConsumer.service;

import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class SQSMessageService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AmazonSQS sqsClient;

    @Value("${jms.queue.name}")
    private String queueName;


    public List<Message> receiveMessage(){
        System.out.println("Receiving messages from MyQueue.\n");
        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(getQueueUrl(queueName));
        final List<Message> msg = sqsClient.receiveMessage(receiveMessageRequest).getMessages();
        for (final Message message : msg) {
            System.out.println("Message");
            System.out.println("  MessageId:     " + message.getMessageId());
            System.out.println("  ReceiptHandle: " + message.getReceiptHandle());
            System.out.println("  MD5OfBody:     " + message.getMD5OfBody());
            System.out.println("  Body:          " + message.getBody());
            Map<String,String> messageAttributes = message.getAttributes();
            for (final Map.Entry<String, String> entry : messageAttributes.entrySet()) {
                System.out.println("Attribute");
                System.out.println("  Name:  " + entry.getKey());
                System.out.println("  Value: " + entry.getValue());
            }
            sqsClient.deleteMessage(getQueueUrl(queueName),message.getReceiptHandle());
            //?getReceiptHandle作用？
            //message进入到queue中应该是poll()为什么是用deleteMessage()方法
        }
        System.out.println();
        return msg;
    }

    public String getQueueUrl(String queueName) {
        GetQueueUrlResult getQueueUrlResult = sqsClient.getQueueUrl(queueName);//return GetQueueUrlResult
        logger.info("QueueUrl: " + getQueueUrlResult.getQueueUrl());//return string
        return getQueueUrlResult.getQueueUrl();

    }




}
