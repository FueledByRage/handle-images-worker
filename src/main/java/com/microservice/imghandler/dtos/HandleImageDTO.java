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

    /*
     * public String getWidth() {
     * return width;
     * }
     * public void setWidth(String width) {
     * this.width = width;
     * }
     * public String getOutpuKey() {
     * return outpuKey;
     * }
     * public void setOutpuKey(String outpuKey) {
     * this.outpuKey = outpuKey;
     * }
     * public String getHeight() {
     * return height;
     * }
     * public void setHeight(String height) {
     * this.height = height;
     * }
     * public String getRegion() {
     * return region;
     * }
     * public void setRegion(String region) {
     * this.region = region;
     * }
     * public String getBucketName() {
     * return bucketName;
     * }
     * public void setBucketName(String bucketName) {
     * this.bucketName = bucketName;
     * }
     * public String getFileName() {
     * return fileName;
     * }
     * public void setFileName(String fileName) {
     * this.fileName = fileName;
     * }
     */
}
