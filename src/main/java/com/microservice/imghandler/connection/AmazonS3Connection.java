package com.microservice.imghandler.connection;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.microservice.imghandler.dtos.HandleImageDTO;

@Component
public class AmazonS3Connection {

    private String accessKeyId = "${access.key.id}";

    private String accessKeySecret = "${access.key.secret}";

    // private String s3RegionName = "${s3.region.name}";

    AmazonS3Connection() {
    }

    public InputStream getObjectReq(HandleImageDTO requestData) {
        AmazonS3 client = getClient(requestData.getRegion());
        GetObjectRequest objectRequest = new GetObjectRequest(requestData.getBucketName(), requestData.getFileName());
        S3Object s3Object = client.getObject(objectRequest);
        return s3Object.getObjectContent();
    }

    public AmazonS3 getClient(String region) {
        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKeyId, accessKeySecret);
        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(basicAWSCredentials);

        AmazonS3 client = AmazonS3ClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(region)
                .build();
        return client;
    }

}
