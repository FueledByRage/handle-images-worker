package com.microservice.imghandler.services;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.microservice.imghandler.connection.AmazonS3Connection;
import com.microservice.imghandler.dtos.HandleImageDTO;
@Component
public class UploadImage {

    @Autowired
    AmazonS3Connection connection;

    public void execute(HandleImageDTO data, ByteArrayOutputStream outputStream ) throws Exception {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(outputStream.size());
        meta.setContentType("image/jpeg");
        PutObjectRequest objectRequest = new PutObjectRequest(data.getBucketName(), data.getOutpuKey(), new ByteArrayInputStream(outputStream.toByteArray()), meta);
        connection.getClient().putObject(objectRequest);
    }
}
