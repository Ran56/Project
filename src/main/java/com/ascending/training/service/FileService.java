package com.ascending.training.service;


import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.common.io.Files;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
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
        String fileName = file.getOriginalFilename();
        //需要使用getOriginalFilename()获取上传文件名称，getName()是获取表单中文件组件的名字
        //若使用则会一直得到fileName为 "file" 而上传文件名
        String[] nameContents = fileName.split("\\.");
        String newName = nameContents[0]+ "-" + uuid + "." + nameContents[nameContents.length-1];
        //String newName = uuid  + "." + Files.getFileExtension(fileName);
        //如果使用Files需要加dependency
//	      <dependency>
//        <groupId>com.google.guava</groupId>
//        <artifactId>guava</artifactId>
//        <version>24.1.1-jre</version>
//        </dependency>

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        PutObjectRequest request = new PutObjectRequest(bucketName, newName, file.getInputStream(),objectMetadata);
        //file.getInputStream(),objectMetadata????
        amazonS3.putObject(request);
        return newName;
    }

    public URL getFileUrl(String s3Key)//s3Key需要包含文件后缀
    {
        LocalDateTime expiration = LocalDateTime.now().plusDays(1);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, s3Key);
        generatePresignedUrlRequest.withMethod(HttpMethod.GET);
        generatePresignedUrlRequest.withExpiration(Date.from(expiration.toInstant(ZoneOffset.UTC)));
        //??
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
        return url;
    }

}
