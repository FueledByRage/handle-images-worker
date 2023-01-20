package com.microservice.imghandler.services;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import com.microservice.imghandler.dtos.HandleImageDTO;

import net.coobird.thumbnailator.Thumbnails;

public class ResizeImage {
    

    public ByteArrayOutputStream resize(HandleImageDTO data, InputStream stream ){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Thumbnails.of(stream).size(Integer.valueOf(data.width), Integer.valueOf(data.height))
                .outputFormat("jpg")
                .toOutputStream(outputStream);
        } catch (Exception e) {
            System.out.println(e);
        }
        return outputStream;
    }
}
