package com.ascending.training.config;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
@Profile("unit")
public class AWSTestConfig {


    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonS3 getAmazonS3()
    {
        return mock(AmazonS3.class);//一定要在test下面才可以找到mock
                                    //mock对象调用任何方法都是null
                                    //不会真的与第三方交互
    }


    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonSQS getAmazonSQS()
    {
        AmazonSQS amazonSQS = mock(AmazonSQS .class);
        GetQueueUrlResult stubResult = mock(GetQueueUrlResult.class);//如果写在getQueueUrl方法内则会在加载Bean的时候报错
        //也可以是GetQueueUrlResult stubResult = new GetQueueUrlResult();
        when(amazonSQS.getQueueUrl(anyString())).thenReturn(stubResult);
        return amazonSQS;
    }

}
