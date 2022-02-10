package com.example.returnkey.returnmanagement.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {

    void handleFileUpload(MultipartFile file) throws Exception;

}
