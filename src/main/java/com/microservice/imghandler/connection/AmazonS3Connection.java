package com.microservice.imghandler.connection;

import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.microservice.imghandler.dtos.HandleImageDTO;

@Configuration
public class AmazonS3Connection {
    

    private static AmazonS3Connection instace = null;
    AWSCredentialsProvider credentialsProvider = DefaultAWSCredentialsProviderChain.getInstance();
    private AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).build();

    private AmazonS3Connection(){}

    @Bean
    public static AmazonS3Connection getInstance(){
        if(instace == null) instace = new AmazonS3Connection();
        return instace;
    }

    public InputStream getObjectReq( HandleImageDTO requestData ){
        AmazonS3 client = AmazonS3ClientBuilder.standard().withRegion(requestData.getRegion()).build();
        GetObjectRequest objectRequest = new GetObjectRequest(requestData.getBucketName(), requestData.getFileName());
        S3Object s3Object = client.getObject(objectRequest);
        return s3Object.getObjectContent();
    }

    public AmazonS3 getClient() {
        return client;
    }

    public void setClient(AmazonS3 client) {
        this.client = client;
    }


}
