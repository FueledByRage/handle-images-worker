package com.microservice.imghandler.services;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.microservice.imghandler.connection.AmazonS3Connection;
import com.microservice.imghandler.dtos.HandleImageDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RequestImage {

    private final AmazonS3Connection connection;

    public InputStream requestImageAsStream(HandleImageDTO data) {
        String bucketName = data.getBucketName();
        String fileName = data.getFileName();
        AmazonS3 client = connection.getClient(data.getRegion());
        GetObjectRequest objectRequest = new GetObjectRequest(bucketName, fileName);
        S3Object s3Image = client.getObject(objectRequest);
        return s3Image.getObjectContent();
    }
}