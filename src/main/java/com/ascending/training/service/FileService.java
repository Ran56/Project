package com.ascending.training.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    private AmazonS3 amazonS3;

    @Value("${bucketName}")
    private String bucketName;

    public FileService (@Autowired AmazonS3 amazonS3)
    {
        this.amazonS3 = amazonS3;
    }


    public void upLoadFile(File file)
    {
        PutObjectRequest request = new PutObjectRequest(bucketName,file.getName(),file);
        amazonS3.putObject(request);

    }

    public String upLoadFile(String bucketName, MultipartFile file) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String fileName = file.getName();
        String[] nameContents = fileName.split("\\.");
        String newName = uuid + "-" + nameContents[0] + "." + nameContents[nameContents.length-1];
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        PutObjectRequest request = new PutObjectRequest(bucketName, file.getName(), file.getInputStream(),objectMetadata);
        amazonS3.putObject(request);
        return newName;
    }


    //在这里写作业

    //最后记得写unit test
}
