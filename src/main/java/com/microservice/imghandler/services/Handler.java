package com.microservice.imghandler.services;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.microservice.imghandler.dtos.HandleImageDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Handler {

    private final RequestImage requestImage;

    private final UploadImage uploadImage;

    private final ResizeImage resizeImage;

    public void handleImage(HandleImageDTO data) {
        try {
            InputStream stream = requestImage.requestImageAsStream(data);

            ByteArrayOutputStream outputStream = resizeImage.resize(data, stream);

            uploadImage.execute(data, outputStream);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
