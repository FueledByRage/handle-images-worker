package com.microservice.imghandler.connection;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.microservice.imghandler.dtos.HandleImageDTO;
@Component
public class AmazonS3Connection {
    

    AmazonS3Connection(){}


    public InputStream getObjectReq( HandleImageDTO requestData ){
        GetObjectRequest objectRequest = new GetObjectRequest(requestData.getBucketName(), requestData.getFileName());
        S3Object s3Object = getClient().getObject(objectRequest);
        return s3Object.getObjectContent();
    }
    
    public AmazonS3 getClient() {
        AWSCredentialsProvider credentialsProvider = DefaultAWSCredentialsProviderChain.getInstance();
        AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).build() ;
        return client;
    }


}
