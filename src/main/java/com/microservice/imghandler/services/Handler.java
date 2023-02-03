package com.microservice.imghandler.services;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.imghandler.connection.AmazonS3Connection;
import com.microservice.imghandler.dtos.HandleImageDTO;

@Service
public class Handler{

    @Autowired
    AmazonS3Connection connection;

    @Autowired
    UploadImage uploadImage;

    @Autowired
    ResizeImage resizeImage;
    
    public void handleImage( HandleImageDTO data ){
        try {
            InputStream stream = connection.getObjectReq(data);
            
            ByteArrayOutputStream outputStream = resizeImage.resize(data, stream);
            
            uploadImage.execute(data, outputStream);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
