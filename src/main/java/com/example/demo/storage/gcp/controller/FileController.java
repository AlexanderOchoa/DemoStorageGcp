package com.example.demo.storage.gcp.controller;

import com.example.demo.storage.gcp.request.SaveFileRequest;
import com.example.demo.storage.gcp.service.FileService;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@Controller
@RequestMapping(value = "/files")
@CrossOrigin(value = "*")
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService clientService) {
        this.fileService = clientService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> save(@RequestBody SaveFileRequest request) throws Exception {
        String result = fileService.save(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{fileName}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<String> delete(@PathVariable(value = "fileName") String fileName) throws Exception {
        String result = fileService.delete(fileName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/donwload/{fileName}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<InputStreamResource> donwload(@PathVariable(value = "fileName") String fileName) throws Exception {
        ByteArrayInputStream result = fileService.donwload(fileName);
        return ResponseEntity.ok().header("Content-Disposition", fileName).contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(result));
    }

}
