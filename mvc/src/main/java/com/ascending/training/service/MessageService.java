package com.ascending.training.service;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String queueName = System.getProperty("queueName");

    private AmazonSQS sqsClient;
//    private AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
//            .withCredentials(new DefaultAWSCredentialsProviderChain())
//            .build();
    private String queueUrl;

    public MessageService(@Autowired AmazonSQS sqsClient)
    {
        this.sqsClient = sqsClient;
        this.queueUrl = getQueueUrl(queueName);
        //写在constructor而不是在method中定义的原因：每次call getQueueUrl()都是一次network request，损耗很大，所以
        //通过constructor一次性创建好存到instance variable中,getQueueUrl()就可以直接使用
    }

    public void sendMessage(String messageBody, Integer delaySec)
    {
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody)
                .withDelaySeconds(delaySec);
        sqsClient.sendMessage(sendMessageRequest);
    }


    public String getQueueUrl(String queueName) {
        //当时用mock AmazonSQS时， 该对象调用任何对象的方法都为null所以如果不用stub将会在这里产生NPE
        GetQueueUrlResult getQueueUrlResult = sqsClient.getQueueUrl(queueName);
        logger.info("QueueUrl: " + getQueueUrlResult.getQueueUrl());
        return getQueueUrlResult.getQueueUrl();
    }


}
