package com.microservice.imghandler.services;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.microservice.imghandler.dtos.HandleImageDTO;
@Component
public class UploadImage {

    

    public void execute(HandleImageDTO data, ByteArrayOutputStream outputStream, AmazonS3 client){
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(outputStream.size());
        meta.setContentType("image/jpeg");
        PutObjectRequest objectRequest = new PutObjectRequest(data.getBucketName(), data.getOutpuKey(), new ByteArrayInputStream(outputStream.toByteArray()), meta);
        client.putObject(objectRequest);
    }
}
