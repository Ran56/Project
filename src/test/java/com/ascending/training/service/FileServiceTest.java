package com.ascending.training.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ascending.training.ApplicationBootstrap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class FileServiceTest {

    @Autowired
    private FileService fileService;
    @Autowired
    private AmazonS3 amazonS3;
    private MultipartFile multipartFile;

    @Before
    public void setUp() throws IOException {
        multipartFile = new MockMultipartFile("hello.txt", new FileInputStream(new File("/Users/ranran/Desktop/AWS/hello.txt")));

    }

    @After
    public void tearDown(){}

    @Test
    public void uploadFileTest() throws IOException {
        fileService.upLoadFile(new File("/Users/ranran/Desktop/AWS/hello.txt"));
        Assert.assertTrue(true);
    }


    @Test
    public void uploadFileTestMock() throws IOException {
        fileService.upLoadFile(new File("/Users/ranran/Desktop/AWS/hello1.txt"));
        verify(amazonS3,times(1)).putObject(any(PutObjectRequest.class));
                                        //verify(amazonS3,times(1))后面紧跟的方法表示什么方法调用1次
                                        //any()不在乎什么样的参数即具体什么值的参数但是在乎什么类型的参数
    }
    @Test
    public void uploadFileTestDuplicate() throws IOException {
        fileService.upLoadFile("ran-s3-bucket-1", multipartFile);
        Assert.assertTrue(true);
    }
}
