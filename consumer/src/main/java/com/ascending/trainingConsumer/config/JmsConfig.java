package com.ascending.trainingConsumer.config;


import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.ascending.trainingConsumer.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



import javax.jms.Session;
import java.io.IOException;

@Configuration
@EnableJms
//@EnableJms,when start spring, this annotation will execute connection.start().
public class JmsConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonSQS getAmazonSQS()
    {
        return AmazonSQSClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Bean(name = "connectionFactory")
    public SQSConnectionFactory getSQSConnectionFactory(@Autowired AmazonSQS amazonSQSClient)
    {
        SQSConnectionFactory factory =
                new SQSConnectionFactory(new ProviderConfiguration(),amazonSQSClient);
        return factory;
    }

    @Bean
    public JmsTemplate getJmsTemplate(@Autowired SQSConnectionFactory connectionFactory)
    {
        JmsTemplate jmsTemplate = new JmsTemplate((connectionFactory));
        return jmsTemplate;
        //when listener listens to message, it will use JmsTemplate and we cannot see the system calls this Bean.
    }

    @Bean("dynamicResolver")
    public DynamicDestinationResolver getDynamicDestinationResolver()
    {
        return new DynamicDestinationResolver();
    }
    //this is for various listeners need to listening message
//    @JmsListener(destination = "project-standard-queue")
//    public void processMessage(String msg){
//        logger.debug(msg);
    //we also can use other destination name @JmsListener(destination = "project-queue1") with destination = "project-standard-queue" at the same time.


    @Bean(name = "jmsListenerContainerFactory")
    @DependsOn({"connectionFactory","dynamicResolver"})
    public DefaultJmsListenerContainerFactory getDefaultJmsListenerContainerFactory
            (@Autowired SQSConnectionFactory connectionFactory,@Autowired DynamicDestinationResolver dynamicDestinationResolver){
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setSessionTransacted(false);
        jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        jmsListenerContainerFactory.setDestinationResolver(dynamicDestinationResolver);
        jmsListenerContainerFactory.setConcurrency("1");//only one listener is allowed to be started under concurrencyn at same time
        jmsListenerContainerFactory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);//if use ACKNOWLEDGE, it needs to call acknowledge method.
        return jmsListenerContainerFactory;}


    @Bean
    public ProcessService getProcessService() throws IOException {
        String ACCOUNT_SID = System.getProperty("ACCOUNT_SID");
        String AUTH_TOKEN = System.getProperty("AUTH_TOKEN");
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        ProcessService processService = new ProcessService();
        return processService;
    }

}
