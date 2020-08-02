package com.ascending.training.service;


import com.amazonaws.services.s3.model.Bucket;
import com.ascending.training.ApplicationBootstrap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class AWSS3ServiceTest {

    @Autowired
    private AWSS3Service awss3Service;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void setUp(){}

    @After
    public  void tearDown(){}

    @Test
    public void testCreateBucket()
    {
        String bucketName = "ran-s3-bucket-4";
        Bucket bucket = awss3Service.createBucket(bucketName);
        Assert.assertNotNull(bucket);
    }










}
