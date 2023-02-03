package com.microservice.imghandler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.microservice.imghandler.dtos.HandleImageDTO;
import com.microservice.imghandler.services.ResizeImage;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class ImageResizeTest {
    
    @Autowired
    ResizeImage resizeImage;
    
    
    @Test
    void testResize(){
        
        try {
            HandleImageDTO data = new HandleImageDTO();
            data.setWidth("200");
            data.setHeight("200");
    
			
			String imageUrl = "https://img.freepik.com/fotos-gratis/o-gato-vermelho-ou-branco-eu-no-estudio-branco_155003-13189.jpg?w=2000";
			URL url = new URL(imageUrl);
			InputStream input = url.openStream();
            ByteArrayOutputStream outPut = resizeImage.resize(data, input);
            File image = new File("src/test/java/com/microservice/imghandler/outputImages/imageFile.jpg");
            FileOutputStream outputStream = new FileOutputStream(image);

            outPut.writeTo(outputStream);
		} catch (Exception e) {
            assertThat(2).isEqualTo(3);
            System.out.println(e);
		}
    }

}