package com.example.returnkey.returnmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.returnkey.returnmanagement.service.IReturnService;
import com.example.returnkey.returnmanagement.service.IUploadService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class BaseController{

    protected Logger logger = LoggerFactory.getLogger(BaseController.class);
    protected Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    @Autowired
    protected IUploadService uploadService;

    @Autowired
    protected IReturnService returnService;

}