package com.example.demo.storage.gcp.service;

import java.io.ByteArrayInputStream;

public interface GoogleStorageService {
    ByteArrayInputStream getFile(String fileName) throws Exception;
    void saveFile(String fileBase64, String fileName) throws Exception;
    void deleteFile(String fileName) throws Exception;
}