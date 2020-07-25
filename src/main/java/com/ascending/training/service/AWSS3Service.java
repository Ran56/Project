package com.ascending.training.service;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AWSS3Service {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AmazonS3 amazonS3;
//    private String myAWSAccessKeyId="";
//    private String myAWSSecretKey="";


    public AWSS3Service(@Autowired AmazonS3 amazonS3)
    {
        //amazonS3 = getS3ClientWithSuppliedCredentials();
       this.amazonS3 = amazonS3;
    }


//    private AmazonS3 getS3ClientWithSuppliedCredentials() { //hard code
//        BasicAWSCredentials awsCreds = new BasicAWSCredentials(myAWSAccessKeyId, myAWSSecretKey);
//
//        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
//                .withRegion("us-east-1")
//                .build();
//        return s3Client;
//    }
//
//    private AmazonS3 getS3ClientUsingDefaultChain() {//自动去vm option找参数  //运用builder的design pattern
//        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                .withRegion(Regions.US_EAST_1)
//                .build();
//        return s3Client;
//    }

    public Bucket createBucket(String bucketName) {
        Bucket bucket = null;
        if(!amazonS3.doesBucketExistV2(bucketName)) {
            bucket = amazonS3.createBucket(bucketName);
        } else {
            logger.info("bucket name: {} is not available."
                    + " Try again with a different Bucket name.", bucketName);
        }
        return bucket;
    }


}
