package com.example.demo.storage.gcp.service;

import com.example.demo.storage.gcp.request.SaveFileRequest;

import java.io.ByteArrayInputStream;

public interface FileService {
    String save(SaveFileRequest request) throws Exception;
    String delete(String fileName) throws Exception;
    ByteArrayInputStream donwload(String nombreArchivo) throws Exception;
}
