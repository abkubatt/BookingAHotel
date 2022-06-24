package com.example.bookinghotel.microservices.json;

import lombok.Data;

@Data
public class FileServiceResponse {
    private String fileName;
    private String downloadUri;
    private String fileType;
    private long size;

    public FileServiceResponse(String fileName, String downloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.downloadUri = downloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
