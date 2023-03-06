package com.microservice.imghandler.connection;

import static org.assertj.core.api.Assertions.assertThat;

import com.microservice.imghandler.dtos.HandleImageDTO;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AmazonS3ConnectionTest {

    @Autowired
    private AmazonS3Connection amazonS3Connection;

    @Test
    public void getObjectReqTest() throws IOException {
        // Specify the Landsat image you want to download
        String bucketName = "landsat-pds";
        String key = "scene_list.gz";
        String region = "s3.us-west-2";

        // Download the image using AmazonS3Connection
        HandleImageDTO handleImageDTO = new HandleImageDTO();
        handleImageDTO.setBucketName(bucketName);
        handleImageDTO.setFileName(key);
        handleImageDTO.setRegion(region);
        handleImageDTO.setOutpuKey("");

        InputStream downloadedInputStream = amazonS3Connection.getObjectReq(handleImageDTO);

        // Verify that the downloaded image is not null
        assertThat(downloadedInputStream).isNotNull();

        // Clean up: no need to delete the image from S3 as it's a public dataset
    }

}
