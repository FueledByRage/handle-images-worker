package com.microservice.imghandler.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.microservice.imghandler.dtos.HandleImageDTO;
import net.coobird.thumbnailator.Thumbnails;


public class Handler{


    public void handleImage( HandleImageDTO data ){
        try {
            AWSCredentialsProvider credentialsProvider = DefaultAWSCredentialsProviderChain.getInstance();
            AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).build();
            S3Object s3Object = getObjectReq(data);
            InputStream stream = s3Object.getObjectContent();
            
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Thumbnails.of(stream).size(Integer.valueOf(data.width), Integer.valueOf(data.height))
            .outputFormat("jpg")
            .toOutputStream(outputStream);

            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(outputStream.size());
            meta.setContentType("image/jpeg");
            PutObjectRequest objectRequest = new PutObjectRequest(data.bucketName, data.outpuKey, new ByteArrayInputStream(outputStream.toByteArray()), meta);
            client.putObject(objectRequest);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }

    private S3Object getObjectReq( HandleImageDTO requestData ){
        AmazonS3 client = AmazonS3ClientBuilder.standard().withRegion(requestData.region).build();
        GetObjectRequest objectRequest = new GetObjectRequest(requestData.bucketName, requestData.fileName);
        S3Object s3Object = client.getObject(objectRequest);
        return s3Object;
    }

}
