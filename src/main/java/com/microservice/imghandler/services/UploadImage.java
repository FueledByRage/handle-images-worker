package com.microservice.imghandler.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.microservice.imghandler.connection.AmazonS3Connection;
import com.microservice.imghandler.dtos.HandleImageDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UploadImage {

    private final AmazonS3Connection connection;

    public void execute(HandleImageDTO data, ByteArrayOutputStream outputStream) throws Exception {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(outputStream.size());
        meta.setContentType("image/jpeg");
        PutObjectRequest objectRequest = new PutObjectRequest(data.getBucketName(), data.getOutpuKey(),
                new ByteArrayInputStream(outputStream.toByteArray()), meta);
        connection.getClient(data.getRegion()).putObject(objectRequest);
    }
}
