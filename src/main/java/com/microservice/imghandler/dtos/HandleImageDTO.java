package com.microservice.imghandler.dtos;

import lombok.Data;

@Data
public class HandleImageDTO {
    private String width;
    private String outpuKey;
    private String height;
    private String region;
    private String bucketName;
    private String fileName;
}
