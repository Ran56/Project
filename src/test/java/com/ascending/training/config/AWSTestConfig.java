package com.ascending.training.config;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import static org.mockito.Mockito.mock;

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
}
