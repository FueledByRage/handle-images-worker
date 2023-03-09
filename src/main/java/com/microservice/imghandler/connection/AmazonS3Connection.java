package com.microservice.imghandler.connection;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Component
public class AmazonS3Connection {

    private String accessKeyId = "${access.key.id}";
    private String accessKeySecret = "${access.key.secret}";

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
