package com.example.demo.storage.gcp.service.impl;

import com.example.demo.storage.gcp.request.SaveFileRequest;
import com.example.demo.storage.gcp.service.FileService;
import com.example.demo.storage.gcp.service.GoogleStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class FileServiceImpl implements FileService {

    private GoogleStorageService googleStorageService;

    @Autowired
    public FileServiceImpl(GoogleStorageService googleStorageService) {
        this.googleStorageService = googleStorageService;
    }

    @Override
    public String save(SaveFileRequest request) throws Exception {
        googleStorageService.saveFile(request.getFileBase64(), request.getFileName());
        return "Register success";
    }

    @Override
    public String delete(String fileName) throws Exception {
        googleStorageService.deleteFile(fileName);
        return "Delete success";
    }

    @Override
    public ByteArrayInputStream donwload(String fileName) throws Exception {
        return googleStorageService.getFile(fileName);
    }

}
