package com.example.returnkey.returnmanagement.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class UploadController extends BaseController {

    @PostMapping(path="/upload",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file){

        Map<String, String> response = new HashMap<>();
        String message = StringUtils.EMPTY;
        try {
            uploadService.handleFileUpload(file);
            message = "upload successful";
        } catch (DataIntegrityViolationException e) {
            message = "data already exists";
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        } 

        response.put("message", message);
        return ResponseEntity.ok().body(gson.toJson(response));
    }

}