package com.microservice.imghandler.services;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.microservice.imghandler.dtos.HandleImageDTO;

@Service
public class Handler{

    @Autowired
    UploadImage uploadImage;

    @Autowired
    ResizeImage resizeImage;
    
    public void handleImage( HandleImageDTO data ){
        try {
            AWSCredentialsProvider credentialsProvider = DefaultAWSCredentialsProviderChain.getInstance();
            AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).build();
            S3Object s3Object = getObjectReq(data);
            InputStream stream = s3Object.getObjectContent();
            
            ByteArrayOutputStream outputStream = resizeImage.resize(data, stream);
            
            uploadImage.execute(data, outputStream, client);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private S3Object getObjectReq( HandleImageDTO requestData ){
        AmazonS3 client = AmazonS3ClientBuilder.standard().withRegion(requestData.getRegion()).build();
        GetObjectRequest objectRequest = new GetObjectRequest(requestData.getBucketName(), requestData.getFileName());
        S3Object s3Object = client.getObject(objectRequest);
        return s3Object;
    }

}
