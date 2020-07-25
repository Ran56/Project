package com.ascending.training.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

@Configuration
@Profile({"dev","prod"})
public class AWSConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonS3 getAmazonS3()
    {
//        String awsKey = System.getProperty("aws.accessKeyId");
//        String awsSecret = System.getProperty("aws.secretKey");
//        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsKey, awsSecret);
//        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
//                .withRegion(Regions.US_EAST_1)
//                .build();
//        return s3Client;

//
//        return  AmazonS3ClientBuilder
//                .standard()
//                .withRegion(Regions.US_EAST_1)
//                .build();


        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(Regions.US_EAST_1)
                .build();







    }





}
