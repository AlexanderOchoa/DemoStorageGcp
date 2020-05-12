package com.example.demo.storage.gcp.request;

public class SaveFileRequest {

    private String fileBase64;
    private String fileName;

    public String getFileBase64() {
        return fileBase64;
    }

    public void setFileBase64(String fileBase64) {
        this.fileBase64 = fileBase64;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "SaveFileRequest{" +
                "fileBase64='" + fileBase64 + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}